/*
 * Copyright (c) 2015 delta
 */
package com.bernatskyi.delta.service;

import com.bernatskyi.delta.entity.Operation;
import com.bernatskyi.delta.entity.Storage;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * @author Yuriy
 */
@Service(OperationService.NAME)
public class OperationServiceBean implements OperationService {
    @Inject
    OperationWorker operationWorker;

    @Override
    public void updateStorageState(Operation operation) {
        operationWorker.recalculateStates(operation, false);
    }

    @Override
    public void removeOperation(Operation operation) {
        operationWorker.recalculateStates(operation, true);
    }
}