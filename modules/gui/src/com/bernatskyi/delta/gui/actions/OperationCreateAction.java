/*
 * Copyright (c) 2015 delta
 */

package com.bernatskyi.delta.gui.actions;

import com.bernatskyi.delta.entity.Operation;
import com.bernatskyi.delta.entity.OperationType;
import com.bernatskyi.delta.entity.Storage;
import com.bernatskyi.delta.entity.StorageCategoryState;
import com.bernatskyi.delta.gui.operation.OperationEdit;
import com.bernatskyi.delta.service.OperationService;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.ListComponent;
import com.haulmont.cuba.gui.components.Window;
import com.haulmont.cuba.gui.components.actions.CreateAction;
import com.haulmont.cuba.gui.components.actions.ItemTrackingAction;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.impl.DatasourceImplementation;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by Yuriy on 21.09.2015.
 */
public class OperationCreateAction extends CreateAction {
    private OperationType type;
    private com.bernatskyi.delta.entity.Operation item;
    private boolean alwaysApplicable;
    private Storage storage;

    private OperationService operationService;

    private CollectionDatasource<StorageCategoryState, UUID> refreshingDs;

    public OperationCreateAction(ListComponent target, WindowManager.OpenType openType, OperationType operationType, com.bernatskyi.delta.entity.Operation item, boolean alwaysApplicable, CollectionDatasource<StorageCategoryState, UUID> refreshingDs) {
        super(target, openType, String.format("delta$Operation%s", operationType.toString())); //todo

        this.operationService = AppBeans.get(OperationService.NAME);
        this.type = operationType;
        this.item = item;
        this.alwaysApplicable = alwaysApplicable;

        this.refreshingDs = refreshingDs;
    }

    public OperationCreateAction(ListComponent target, WindowManager.OpenType openType, OperationType operationType, Storage storage, boolean alwaysApplicable, CollectionDatasource<StorageCategoryState, UUID> refreshingDs) {
        super(target, openType, String.format("delta$Operation%s", operationType.toString())); //todo

        this.operationService = AppBeans.get(OperationService.NAME);
        this.type = operationType;
        this.storage = storage;
        this.alwaysApplicable = alwaysApplicable;
        this.refreshingDs = refreshingDs;
    }

    @Override
    public void actionPerform(Component component) {
        if(target.getSingleSelected() == null || !(target.getSingleSelected() instanceof Storage)) {
            super.actionPerform(component);
        } else {
            target.getFrame().openEditor("delta$Operation.edit", item, WindowManager.OpenType.DIALOG, getWindowParams());
        }
    }

    @Override
    public Map<String, Object> getWindowParams() {
        HashMap<String, Object> params = new HashMap<String, Object>();

        StandardEntity entity = target.getSingleSelected();

        if(entity instanceof Storage) {
            params.put(OperationEdit.STORAGE_PARAM_NAME, entity);
        } else {
            params.put(OperationEdit.STORAGE_PARAM_NAME, storage);
        }

        params.put(OperationEdit.OPERATION_TYPE_PARAM_NAME, type);

        return params;
    }

    @Override
    protected boolean isApplicable() {
        return alwaysApplicable || target != null && !target.getSelected().isEmpty();
    }

    @Override
    protected void afterCommit(Entity entity) {
        operationService.updateStorageState((com.bernatskyi.delta.entity.Operation) entity);

        if(refreshingDs != null) {
            refreshingDs.refresh();
        }
    }
}
