/*
 * Copyright (c) 2015 delta
 */

package com.bernatskyi.delta.gui.reporting.storage;

import com.bernatskyi.delta.entity.reporting.storagereport.StorageReportCategoryStates;
import com.haulmont.cuba.gui.data.impl.CollectionDatasourceImpl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Yuriy on 23.12.2015.
 */
public class StorageReportCategoryStatesDatasource extends CollectionDatasourceImpl<StorageReportCategoryStates, UUID> {
    @Override
    protected void loadData(Map<String, Object> params) {
        data.clear();

        dataLoadError = null;
        try {
            List<StorageReportCategoryStates> states = (List<StorageReportCategoryStates>) params.get("data");

            for (StorageReportCategoryStates state : states) {
                data.put(state.getId(), state);
            }
        } catch (Exception e) {
            dataLoadError = e;
        }
    }
}
