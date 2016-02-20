/*
 * Copyright (c) 2016 delta
 */
package com.bernatskyi.delta.gui.reporting.storage;

import com.bernatskyi.delta.entity.reporting.storagereport.StorageReportCategoryStates;
import com.bernatskyi.delta.entity.reporting.storagereport.StorageReportEntry;
import com.haulmont.cuba.gui.components.AbstractWindow;
import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.data.CollectionDatasource;

import javax.inject.Named;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

/**
 * @author Yuriy
 */
public class StorageReportEntryFrame extends AbstractFrame {
    @Named("simpleStorageReportCategoryStatesDatasource")
    CollectionDatasource<StorageReportCategoryStates, UUID>  simpleStorageReportCategoryStatesDatasource;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        StorageReportEntry entry = (StorageReportEntry) params.get("data");

        simpleStorageReportCategoryStatesDatasource.refresh(Collections.<String, Object>singletonMap("data", entry.getSimpleStates()));
    }
}