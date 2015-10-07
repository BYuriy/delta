/*
 * Copyright (c) 2015 delta
 */

package com.bernatskyi.delta.gui.actions;

import com.bernatskyi.delta.entity.StorageCategoryState;
import com.bernatskyi.delta.service.OperationService;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.gui.components.ListComponent;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.components.actions.RemoveAction;
import com.haulmont.cuba.gui.data.CollectionDatasource;

import java.util.Set;
import java.util.UUID;

/**
 * Created by Yuriy on 01.10.2015.
 */
public class OperationRemoveAction extends RemoveAction {
    private OperationService operationService;

    private CollectionDatasource<StorageCategoryState, UUID> refreshingDs;

    public OperationRemoveAction(ListComponent target, CollectionDatasource<StorageCategoryState, UUID> refreshingDs) {
        super(target, true, "delta$OperationRemoveAction");

        this.operationService = AppBeans.get(OperationService.NAME);
        this.refreshingDs = refreshingDs;
    }

    @Override
    protected void afterRemove(Set selected) {
        operationService.removeOperation((com.bernatskyi.delta.entity.Operation) selected.iterator().next());

        refreshingDs.refresh();
    }
}
