/*
 * Copyright (c) 2015 delta
 */
package com.bernatskyi.delta.gui.operation;

import com.bernatskyi.delta.entity.*;
import com.bernatskyi.delta.entity.materials.Category;
import com.bernatskyi.delta.service.OperationService;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.haulmont.cuba.gui.components.Field;
import com.haulmont.cuba.gui.data.ValueListener;

import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * @author Yuriy
 */
public class OperationEdit extends AbstractEditor<Operation> {
    public static final String STORAGE_PARAM_NAME = "storage";
    public static final String OPERATION_TYPE_PARAM_NAME = "type";

    private OperationType type;
    private Storage storage;

    @Named("fieldGroup.storage")
    private Field storageField;

    @Named("fieldGroup.type")
    private Field operationTypeField;

    @Named("fieldGroup.destination")
    private Field storageDestinationField;

    @Named("fieldGroup.dateTime")
    private Field dateTimeField;

    @Named("fieldGroup.summaryPrice")
    private Field summaryPriceField;

    @Inject
    private DataManager dataManager;

    @Override
    public void init(Map<String, Object> params) {
        storage = (Storage) params.get(STORAGE_PARAM_NAME);
        type = (OperationType) params.get(OPERATION_TYPE_PARAM_NAME);

        if (storage != null) {
            storageField.setVisible(false);
        }

        if (type != null) {
            operationTypeField.setVisible(false);
        }

        changeFieldsForMoveType(type, true);

        operationTypeField.addListener(new ValueListener() {
            @Override
            public void valueChanged(Object source, String property, Object prevValue, Object value) {
                OperationType type = (OperationType) value;

                changeFieldsForMoveType(type, false);
            }
        });
    }

    private void changeFieldsForMoveType(OperationType operationType, boolean isInit) {
        if (OperationType.MOVE.equals(operationType)) {
            storageDestinationField.setVisible(true);
            storageDestinationField.setRequired(true);

            summaryPriceField.setVisible(false);
            summaryPriceField.setRequired(false);
        } else {
            storageDestinationField.setVisible(false);
            storageDestinationField.setRequired(false);

            summaryPriceField.setVisible(true);
            summaryPriceField.setRequired(true);

            if(!isInit) {
                Operation operation = getItem();

                if (operation.getDestination() != null) {
                    operation.setDestination(null);
                }
            }
        }
    }

    @Override
    protected void initNewItem(Operation item) {
        item.setType(type);
        item.setStorage(storage);
        item.setDateTime(new Date());
    }

    @Override
    protected boolean preCommit() {
        Operation operation = getItem();

        if(OperationType.MOVE.equals(operation.getType())){
            StorageCategoryState state = loadState(operation.getStorage(), operation.getCategory());

            operation.setSummaryPrice(state.getAveragePrice().multiply(BigDecimal.valueOf(operation.getVolume())));
        }


        return true;
    }

    @Override
    protected boolean postCommit(boolean committed, boolean close) {
        return true;
    }

    private StorageCategoryState loadState(Storage storage, Category category) {
        LoadContext loadContext = new LoadContext(StorageCategoryState.class);
        loadContext.setView(com.haulmont.cuba.core.global.View.LOCAL);
        loadContext.setQueryString("select s from delta$StorageCategoryState s where s.storage.id = :storageId and s.category.id = :categoryId")
                .setParameter("storageId", storage.getId())
                .setParameter("categoryId", category.getId());


        return dataManager.load(loadContext);
    }
}