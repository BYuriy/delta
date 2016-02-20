/*
 * Copyright (c) 2015 delta
 */

package com.bernatskyi.delta.gui.actions;

import com.bernatskyi.delta.entity.Operation;
import com.bernatskyi.delta.entity.StorageCategoryState;
import com.bernatskyi.delta.service.OperationService;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.ListComponent;
import com.haulmont.cuba.gui.components.actions.EditAction;
import com.haulmont.cuba.gui.data.CollectionDatasource;

import java.util.UUID;

/**
 * Created by Yuriy on 18.10.2015.
 */
public class OperationEditAction  extends EditAction{
    private OperationService operationService;
    private com.bernatskyi.delta.entity.Operation old;

    private CollectionDatasource<StorageCategoryState, UUID> refreshingDs;

    public OperationEditAction(ListComponent target, CollectionDatasource<StorageCategoryState, UUID> refreshingDs) {
        super(target, WindowManager.OpenType.DIALOG);

        this.operationService = AppBeans.get(OperationService.NAME);
        this.refreshingDs = refreshingDs;
    }

    @Override
    public void actionPerform(Component component) {
        //todo bad code
        com.bernatskyi.delta.entity.Operation operation = (com.bernatskyi.delta.entity.Operation) target.getSelected().iterator().next();

        old = target.getDatasource().getDataSupplier().newInstance(target.getDatasource().getMetaClass());
        old.setSummaryPrice(operation.getSummaryPrice());
        old.setType(operation.getType());
        old.setStorage(operation.getStorage());
        old.setVolume(operation.getVolume());
        old.setCategory(operation.getCategory());
        old.setDestination(operation.getDestination());

        super.actionPerform(component);
    }

    @Override
    protected void afterCommit(Entity entity) {
        operationService.editOperation(old, (com.bernatskyi.delta.entity.Operation) entity);

        if(refreshingDs != null) {
            refreshingDs.refresh();
        }
    }
}
