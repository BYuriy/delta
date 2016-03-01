/*
 * Copyright (c) 2016 delta
 */

package com.bernatskyi.delta.gui.reporting.mainreport;

import com.bernatskyi.delta.entity.reporting.mainreport.BoughtData;
import com.haulmont.cuba.gui.data.impl.CollectionDatasourceImpl;

import java.util.Map;
import java.util.UUID;

/**
 * Created by Yuriy on 21.02.2016.
 */
public class BoughtDataDatasource extends CollectionDatasourceImpl<BoughtData, UUID> {
    @Override
    protected void loadData(Map<String, Object> params) {
        super.loadData(params);
    }
}
