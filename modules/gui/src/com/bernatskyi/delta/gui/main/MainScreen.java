/*
 * Copyright (c) 2015 delta
 */
package com.bernatskyi.delta.gui.main;

import com.bernatskyi.delta.entity.Operation;
import com.bernatskyi.delta.entity.OperationType;
import com.bernatskyi.delta.gui.actions.OperationCreateAction;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.components.actions.ItemTrackingAction;
import com.haulmont.cuba.gui.data.Datasource;

import javax.inject.Named;
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

    @Named("windowActions.sortBtn")
    private Button sortBtn;

    @Named("windowActions.removeBtn")
    private Button removeBtn;

    @Named("windowActions.editBtn")
    private Button editBtn;

    @Named("storagesTable")
    private Table storagesTable;

    @Named("operationDs")
    private Datasource<Operation> operationDs;

    @Override
    public void init(Map<String, Object> params) {
        initOperationAction(OperationType.BUY, buyBtn);
        initOperationAction(OperationType.SELL, sellBtn);
        initOperationAction(OperationType.MOVE, moveBtn);
        initOperationAction(OperationType.SORT, sortBtn);
        initOperationAction(OperationType.SURPLUS, surplusBtn);
        initOperationAction(OperationType.SHORTFALL, shortfallBtn);
        initOperationAction(OperationType.REFUND, refundBtn);

        removeBtn.setVisible(false);
        editBtn.setVisible(false);

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
        OperationCreateAction action = new OperationCreateAction(storagesTable, WindowManager.OpenType.DIALOG, type, false, null);

        storagesTable.addAction(action);
        button.setAction(action);
    }
}