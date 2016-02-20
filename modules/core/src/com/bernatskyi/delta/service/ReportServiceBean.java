/*
 * Copyright (c) 2015 delta
 */
package com.bernatskyi.delta.service;

import com.bernatskyi.delta.entity.Operation;
import com.bernatskyi.delta.entity.OperationType;
import com.bernatskyi.delta.entity.Storage;
import com.bernatskyi.delta.entity.StorageCategoryState;
import com.bernatskyi.delta.entity.materials.Category;
import com.bernatskyi.delta.entity.materials.Group;
import com.bernatskyi.delta.entity.reporting.mainreport.*;
import com.bernatskyi.delta.entity.reporting.storagereport.StorageReportCategoryState;
import com.bernatskyi.delta.entity.reporting.storagereport.StorageReportCategoryStates;
import com.bernatskyi.delta.entity.reporting.storagereport.StorageReportEntry;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.core.global.View;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author Yuriy
 */
@Service(ReportService.NAME)
public class ReportServiceBean implements ReportService {

    @Inject
    DataManager dataManager;

    @Override
    public Collection<StorageReportEntry> calculateReportEntries(Collection<Storage> storages, Date date) {
        date = DateUtils.round(date, Calendar.DAY_OF_MONTH);

        List<StorageReportEntry> entries = new ArrayList<>();

        for (Storage storage : storages) {
            entries.add(calculateReportEntry(storage, date));
        }

        return entries;
    }

    public MainReportData calculateMainReport(Collection<Storage> storages, Date calculateFrom, Date startDate, Date endDate) {
        //rounding dates to midnights
        calculateFrom = DateUtils.round(calculateFrom, Calendar.DAY_OF_MONTH);
        startDate = DateUtils.round(startDate, Calendar.DAY_OF_MONTH);
        endDate = DateUtils.addDays(DateUtils.round(endDate, Calendar.DAY_OF_MONTH), 1);//add one day to end date and round it to midnight todo check maybe better to round to DAY_OF_YEAR

        MainReportData mainReportData = new MainReportData();

        List<StorageCategoryState> states = loadStates(storages, endDate, "stateWithStorageAndCategory");

        Date maxDate = endDate;
        for (StorageCategoryState state : states) {
            if(state.getDateTime() != null && state.getDateTime().after(maxDate)) {
                maxDate = state.getDateTime();
            } else if(state.getDateTime() == null) {
                maxDate = DateUtils.addDays(new Date(), 1);
            }

            mainReportData.addStorageCategoryState(state);
        }

        List<Operation> operations = loadAllOperations(storages, calculateFrom, maxDate, "operation-storage-category");

        initMainReportData(mainReportData, operations, calculateFrom, startDate, endDate, states);

        return mainReportData;
    }

    private void initMainReportData(MainReportData mainReportData, List<Operation> operations, Date calculateFrom, Date startDate, Date endDate, List<StorageCategoryState> states) {

        Date current = null;
        Date prevCurrent = null;
        MainReportEntry currentEntry = null;

        for(Operation operation : operations) {
            Category category = operation.getCategory();
            Storage storage = operation.getStorage();
            Storage destination = operation.getDestination();
            Date operationDate = operation.getDateTime();

            StorageCategoryState state = mainReportData.getStorageCategoryState(storage, category);
            StorageCategoryState destinationState = mainReportData.getStorageCategoryState(destination, category);

            if(operationDate.before(endDate)) {
                if(operationDate.after(startDate) || DateUtils.isSameDay(startDate, operationDate)) {
                    calculateDailyOperationData(operation, currentEntry);

                    if(current == null || !DateUtils.isSameDay(current, operationDate)) {
                        currentEntry = mainReportData.getMainReportEntryByDate(current);
                        calculateBalanceData(states, currentEntry);

                        current = DateUtils.round(operationDate, Calendar.DAY_OF_MONTH);
                    }
                }

                calculateAccumulatedOperationData(operation, currentEntry);
            }

            recalculateState(state, operation, false);
            if(OperationType.MOVE.equals(operation.getType())) {
                recalculateState(destinationState, operation, true);
            }
        }
    }

    private void calculateDailyOperationData(Operation operation, MainReportEntry entry) {
        if(OperationType.BUY.equals(operation.getType())) {
            calculateBoughData(operation, entry.getDailyBoughtData(operation.getCategory()));
        }

        if(OperationType.SELL.equals(operation.getType())) {
            calculateSellData(operation, entry.getDailySellData(operation.getCategory()));
        }
    }

    private void calculateBoughData(Operation operation,  BoughtData boughData) {
        StorageStateData storageStateData = boughData.getStorageStateData(operation.getStorage());
        storageStateData.setVolume(storageStateData.getVolume() + operation.getVolume());
        boughData.setSummaryPrice(boughData.getSummaryPrice().add(operation.getSummaryPrice()));
    }

    private void calculateSellData(Operation operation, SellData sellData) {
        StorageStateData storageStateData = sellData.getStorageStateData(operation.getStorage());
        storageStateData.setVolume(storageStateData.getVolume() + operation.getVolume());
    }

    private void calculateAccumulatedOperationData(Operation operation, MainReportEntry entry) {
        if(OperationType.BUY.equals(operation.getType())) {
            calculateBoughData(operation, entry.getAccumulatedBoughtData(operation.getCategory()));
        }

        if(OperationType.SELL.equals(operation.getType())) {
            calculateSellData(operation, entry.getAccumulatedSellData(operation.getCategory()));
        }
    }

    private void calculateBalanceData(List<StorageCategoryState> states, MainReportEntry mainReportEntry) {
        for(StorageCategoryState state : states) {
            BalanceData balanceData = mainReportEntry.getBalanceData(state.getCategory());

            StorageStateData storageStateData = balanceData.getStorageStateData(state.getStorage());

            storageStateData.setVolume(state.getVolume());

            balanceData.setSummaryVolume(balanceData.getSummaryVolume() + state.getVolume());
            balanceData.setSummaryPrice(balanceData.getSummaryPrice().add(state.getSummaryPrice()));
            //todo realization price
        }
    }

    private void recalculateState(StorageCategoryState state, Operation operation, boolean isDestination){
        int multiplier = isDestination ? operation.getType().getMultiplier() : (-1) * operation.getType().getMultiplier();
        double operationVolume = operation.getVolume() * multiplier;
        BigDecimal operationPrice = operation.getSummaryPrice().multiply(new BigDecimal(multiplier));

        double newVolume = state.getVolume() + operationVolume;
        BigDecimal newPrice = state.getSummaryPrice().add(operationPrice);

        state.setVolume(newVolume);
        state.setSummaryPrice(newPrice);
    }

    private void recalculateMainReportEntry(MainReportEntry mainReportEntry, Operation operation) {

    }

    private StorageReportEntry calculateReportEntry(Storage storage, Date date) {
        StorageReportEntry result = new StorageReportEntry();
        result.setStorage(storage);
        result.setDate(date);

        List<StorageCategoryState> states = loadStates(storage, date);

        for (StorageCategoryState state : states) {
            List<Operation> operations = loadOperations(storage, state.getCategory(), DateUtils.addDays(date, 1), DateUtils.addDays(new Date(), 1)); //todo from state date and this is all fucking hardcode

            StorageReportCategoryStates storageReportCategoryStates = new StorageReportCategoryStates();
            storageReportCategoryStates.setCategory(state.getCategory());
            storageReportCategoryStates.setStateOnEnd(calculateStateOnStartOrEnd(state.getVolume(), state.getSummaryPrice(), operations));

            operations = loadOperations(storage, state.getCategory(), date, DateUtils.addDays(date, 1));

            storageReportCategoryStates.setStateOnStart(calculateStateOnStartOrEnd(storageReportCategoryStates.getStateOnEnd().getVolume(), storageReportCategoryStates.getStateOnEnd().getPrice(), operations));
            storageReportCategoryStates.setSaledState(calculateOperationState(operations, OperationType.SELL));
            storageReportCategoryStates.setBoughtState(calculateOperationState(operations, OperationType.BUY));
            storageReportCategoryStates.setMovedFromState(calculateOperationState(operations, OperationType.MOVE));

            operations = loadMovedToOperations(storage, state.getCategory(), date, DateUtils.addDays(date, 1));

            storageReportCategoryStates.setMovedToState(calculateOperationState(operations, OperationType.MOVE));

            if (result.getSimpleStates() == null) {
                result.setSimpleStates(new ArrayList<StorageReportCategoryStates>());
            }
            result.getSimpleStates().add(storageReportCategoryStates);
        }

        return result;
    }

    private StorageReportCategoryState calculateOperationState(List<Operation> operations, OperationType operationType) {
        StorageReportCategoryState result = new StorageReportCategoryState();

        double volume = 0;
        BigDecimal price = BigDecimal.ZERO;

        for (Operation operation : operations) {
            if (operation.getType().equals(operationType)) {
                volume = volume + operation.getVolume();
                price = price.add(operation.getSummaryPrice());
            }
        }

        result.setVolume(volume);
        result.setPrice(price);

        return result;
    }

    private StorageReportCategoryState calculateStateOnStartOrEnd(double initialVolume, BigDecimal initialSum, List<Operation> operations) {
        StorageReportCategoryState result = new StorageReportCategoryState();

        for (Operation operation : operations) {
            initialVolume = initialVolume + operation.getVolume() * operation.getType().getMultiplier() * (-1);
            initialSum = initialSum.add(operation.getSummaryPrice().multiply(BigDecimal.valueOf(operation.getType().getMultiplier() * (-1))));
        }

        result.setVolume(initialVolume);
        result.setPrice(initialSum);

        return result;
    }

    private List<Operation> loadOperations(Storage storage, Category category, Date from, Date till) {
        LoadContext context = new LoadContext(Operation.class);
        context.setView("operation-storage-category");
        context.setQueryString("select o from delta$Operation o where o.storage.id = :storageId and o.category.id = :categoryId and o.dateTime >= :from and o.dateTime < :till order by o.dateTime desc")
                .setParameter("storageId", storage.getId())
                .setParameter("categoryId", category.getId())
                .setParameter("from", from)
                .setParameter("till", till);

        return dataManager.loadList(context);
    }

    private List<Operation> loadAllOperations(Collection<Storage> storages, Date from, Date till, String view) {
        Storage[] storagesArray = storages.toArray(new Storage[storages.size()]);

        LoadContext context = new LoadContext(Operation.class);
        context.setView(view);
        context.setQueryString(
                "select o from delta$Operation o " +
                        "where (o.storage in :storages or o.destination.id = :storageId) " +
                        "and o.category.id = :categoryId and o.dateTime >= :from and o.dateTime < :till " +
                        "order by o.dateTime desc")
                .setParameter("storages", storagesArray)
                .setParameter("from", from)
                .setParameter("till", till);

        return dataManager.loadList(context);
    }

    private List<Operation> loadMovedToOperations(Storage storage, Category category, Date from, Date till) {
        LoadContext context = new LoadContext(Operation.class);
        context.setView("operation-storage-category");
        context.setQueryString(
                "select o from delta$Operation o " +
                        "where o.destination.id = :storageId " +
                        "and o.category.id = :categoryId " +
                        "and o.dateTime >= :from and o.dateTime < :till " +
                        "order by o.dateTime desc")
                .setParameter("storageId", storage.getId())
                .setParameter("categoryId", category.getId())
                .setParameter("from", from)
                .setParameter("till", till);

        return dataManager.loadList(context);
    }


    private List<StorageCategoryState> loadStates(Storage storage, Date date) {
        LoadContext context = new LoadContext(StorageCategoryState.class);
        context.setView("stateWithStorageAndCategory");
        context.setQueryString("select scs from delta$StorageCategoryState scs where scs.storage.id = :storageId and (scs.dateTime > :date or scs.dateTime is null)")
                .setParameter("storageId", storage.getId())
                .setParameter("date", date);

        return dataManager.loadList(context);
    }

    private List<StorageCategoryState> loadStates(Collection<Storage> storages, Date date, String view) {
        Storage[] storagesArray = storages.toArray(new Storage[storages.size()]);

        LoadContext context = new LoadContext(StorageCategoryState.class);
        context.setView(view);
        context.setQueryString("select scs from delta$StorageCategoryState scs where scs.storage in :storages and (scs.dateTime > :date or scs.dateTime is null)")
                .setParameter("storages", storagesArray)
                .setParameter("date", date);

        return dataManager.loadList(context);
    }


}