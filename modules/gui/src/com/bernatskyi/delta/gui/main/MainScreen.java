/*
 * Copyright (c) 2015 delta
 */
package com.bernatskyi.delta.gui.main;

import com.bernatskyi.delta.entity.Operation;
import com.bernatskyi.delta.entity.OperationType;
import com.bernatskyi.delta.gui.actions.OperationAction;
import com.bernatskyi.delta.gui.storage.StorageEdit;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.components.actions.BaseAction;
import com.haulmont.cuba.gui.components.actions.ItemTrackingAction;
import com.haulmont.cuba.gui.data.Datasource;

import javax.inject.Named;
import java.util.Collections;
import java.util.Map;

/**
 * @author Yuriy
 */
public class MainScreen extends AbstractWindow {
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

    @Named("windowActions.removeBtn")
    private Button removeBtn;

    @Named("storagesTable")
    private Table storagesTable;

    @Named("operationDs")
    private Datasource<Operation> operationDs;

    @Override
    public void init(Map<String, Object> params) {
        initOperationAction(OperationType.BUY, buyBtn);
        initOperationAction(OperationType.SELL, sellBtn);
        initOperationAction(OperationType.MOVE, moveBtn);
        initOperationAction(OperationType.SURPLUS, surplusBtn);
        initOperationAction(OperationType.SHORTFALL, shortfallBtn);
        initOperationAction(OperationType.REFUND, refundBtn);

        removeBtn.setVisible(false);

        //todo bernatskyi check docs to find standart solution
        ItemTrackingAction viewAction = new ItemTrackingAction(storagesTable, "delta$Storage.view") {
            @Override
            public void actionPerform(Component component) {
                target.getFrame().openEditor("delta$Storage.view", target.getSingleSelected(),
                        WindowManager.OpenType.THIS_TAB);
            }
        };
        storagesTable.addAction(viewAction);
        viewBtn.setAction(viewAction);

        storagesTable.setItemClickAction(viewAction);
        storagesTable.setEnterPressAction(viewAction);
    }

    private void initOperationAction(OperationType type, Button button) {
        //todo move to frame with buttons
        OperationAction action = new OperationAction(storagesTable, WindowManager.OpenType.DIALOG, type,
                (Operation) operationDs.getDataSupplier().newInstance(operationDs.getMetaClass()), false);

        storagesTable.addAction(action);
        button.setAction(action);
    }
}