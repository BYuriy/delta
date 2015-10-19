/*
 * Copyright (c) 2015 delta
 */
package com.bernatskyi.delta.service;

import com.bernatskyi.delta.entity.Operation;

/**
 * @author Yuriy
 */
public interface OperationService {
    String NAME = "delta_OperationService";

    public void updateStorageState(Operation operation);

    public void removeOperation(Operation operation);

    public void editOperation(Operation old, Operation updated);
}