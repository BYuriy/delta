/*
 * Copyright (c) 2015 delta
 */

package com.bernatskyi.delta.service;

import com.bernatskyi.delta.entity.*;
import com.bernatskyi.delta.entity.materials.Category;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.global.CommitContext;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yuriy on 23.09.2015.
 */
@ManagedBean(OperationWorker.NAME)
public class OperationWorker {
    public static final String NAME = "delta_OperationWorker";

    @Inject
    protected Persistence persistence;

    @Inject
    protected DataManager dataManager;

    public void recalculateStates(Operation operation, boolean isRemove) {
        Storage storage = operation.getStorage();
        Category category = operation.getCategory();

        LoadContext context = new LoadContext(StorageCategoryState.class);
        context.setView("storageCategoryState-view");
        context.setQueryString("select s from delta$StorageCategoryState s where (s.storage.id = :stateId or s.storage.id = :destinationStorageId) and s.category.id = :categoryId")
                .setParameter("stateId", storage.getId())
                .setParameter("categoryId", category.getId())
                .setParameter("destinationStorageId", operation.getDestination());

        List<StorageCategoryState> states = dataManager.loadList(context);

        List<StorageCategoryState> statesForUpdate = new ArrayList<>();

        StorageCategoryState sourceState = getState(states, storage, category);
        int multiplier = !isRemove ? operation.getType().getMultiplier() : (-1) * operation.getType().getMultiplier();
        recalculateState(multiplier, operation, sourceState);

        statesForUpdate.add(sourceState);

        if (OperationType.MOVE.equals(operation.getType())) {
            StorageCategoryState destinationState = getState(states, operation.getDestination(), category);
            recalculateState((-1) * multiplier, operation, destinationState);

            statesForUpdate.add(destinationState);
        }

        CommitContext commitContext = new CommitContext(statesForUpdate);
        dataManager.commit(commitContext);
    }

    private StorageCategoryState getState(List<StorageCategoryState> states, Storage storage, Category category) {
        for (StorageCategoryState state : states) {
            if (state.getStorage().equals(storage)) {
                return state;
            }
        }

        StorageCategoryState state = new StorageCategoryState();
        state.setStorage(storage);
        state.setCategory(category);
        state.setVolume(0.0);
        state.setSummaryPrice(BigDecimal.ZERO);

        return state;
    }

    private void recalculateState(int multiplier, Operation operation, StorageCategoryState state) {
        state.setVolume(state.getVolume() + multiplier * operation.getVolume());

        BigDecimal summaryPriceWithMultiplier = operation.getSummaryPrice().multiply(BigDecimal.valueOf(multiplier));
        BigDecimal newPrice = state.getSummaryPrice().add(summaryPriceWithMultiplier);
        state.setSummaryPrice(newPrice);
    }
}
