/*
 * Copyright (c) 2015 delta
 */
package com.bernatskyi.delta.gui.storage;

import com.bernatskyi.delta.entity.Operation;
import com.bernatskyi.delta.entity.OperationType;
import com.bernatskyi.delta.entity.Storage;
import com.bernatskyi.delta.entity.StorageCategoryState;
import com.bernatskyi.delta.gui.actions.OperationCreateAction;
import com.bernatskyi.delta.gui.actions.OperationEditAction;
import com.bernatskyi.delta.gui.actions.OperationRemoveAction;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.WindowParams;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.Datasource;

import javax.annotation.Nullable;
import javax.inject.Named;
import java.util.Map;
import java.util.UUID;

/**
 * @author Yuriy
 */
public class StorageView extends AbstractEditor<Storage> {
    @Named("fieldGroup.name")
    Field nameField;

    @Named("windowActions.viewBtn")
    private Button viewBtn;

    @Named("windowActions.buyBtn")
    private Button buyBtn;

    @Named("windowActions.sellBtn")
    private Button sellBtn;

    @Named("windowActions.moveBtn")
    private Button moveBtn;

    @Named("windowActions.surplusBtn")
    private Button surplusBtn;

    @Named("windowActions.shortfallBtn")
    private Button shortfallBtn;

    @Named("windowActions.refundBtn")
    private Button refundBtn;

    @Named("windowActions.sortBtn")
    private Button sortBtn;

    @Named("windowActions.removeBtn")
    private Button removeBtn;

    @Named("windowActions.editBtn")
    private Button editBtn;

    @Named("operationsTable")
    private Table operationsTable;

    @Named("storageDs")
    private Datasource<Storage> storageDatasource;

    @Named("storageCategoriesStatesDs")
    private CollectionDatasource<StorageCategoryState, UUID> storageCategoriesStatesDs;

    @Named("operationsDs")
    private CollectionDatasource<Operation, UUID> operationsDs;

    @Override
    public void init(Map<String, Object> params) {
        nameField.setEditable(false);

        viewBtn.setVisible(false);

        Storage storage = WindowParams.ITEM.getEntity(params);

        initActions(storage);

        operationsTable.setStyleProvider(new Table.StyleProvider() {
            @Nullable
            @Override
            public String getStyleName(Entity entity, String property) {
                Operation operation = (Operation) entity;

                if(operation.getType().getMultiplier() > 0
                        || (OperationType.MOVE.equals(operation.getType())
                            && operation.getDestination().equals(storageDatasource.getItem()))) {
                    return "increasing-operation";
                } else {
                    return "decreasing-operation";
                }
            }
        });
    }

    private void initActions(Storage storage) {
        initOperationAction(OperationType.BUY, buyBtn, storage);
        initOperationAction(OperationType.SELL, sellBtn, storage);
        initOperationAction(OperationType.MOVE, moveBtn, storage);
        initOperationAction(OperationType.SORT, sortBtn, storage);
        initOperationAction(OperationType.SURPLUS, surplusBtn, storage);
        initOperationAction(OperationType.SHORTFALL, shortfallBtn, storage);
        initOperationAction(OperationType.REFUND, refundBtn, storage);

        OperationRemoveAction removeAction = new OperationRemoveAction(operationsTable, storageCategoriesStatesDs);
        operationsTable.addAction(removeAction);
        removeBtn.setAction(removeAction);

        OperationEditAction editAction = new OperationEditAction(operationsTable, storageCategoriesStatesDs);
        operationsTable.addAction(editAction);
        editBtn.setAction(editAction);
    }

    private void initOperationAction(OperationType type, Button button, Storage storage) {
        //todo move to frame with buttons
//        Operation item = (Operation) operationsTable.getDatasource().getDataSupplier().newInstance(operationsTable.getDatasource().getMetaClass());

        OperationCreateAction action = new OperationCreateAction(operationsTable, WindowManager.OpenType.DIALOG, type, storage, true, storageCategoriesStatesDs);
        operationsTable.addAction(action);
        button.setAction(action);
    }
}