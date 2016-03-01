/*
 * Copyright (c) 2016 delta
 */
package com.bernatskyi.delta.gui.reporting.mainreport;

import com.bernatskyi.delta.entity.Storage;
import com.bernatskyi.delta.entity.reporting.mainreport.BoughtData;
import com.bernatskyi.delta.entity.reporting.mainreport.MainReportEntry;
import com.bernatskyi.delta.entity.reporting.mainreport.StorageStateData;
import com.haulmont.cuba.gui.components.AbstractWindow;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.Datasource;

import javax.inject.Inject;
import java.util.*;

/**
 * @author Yuriy
 */
public class MainReportBoughtDataFrame extends OperationDataAbstractFrame<BoughtData> {
    @Inject
    Table dailyBoughtDataTable;

    @Inject
    Table accumulatedBoughDataTable;

    @Inject
    CollectionDatasource<BoughtData, UUID> dailyBoughtDataListDs;

    @Inject
    CollectionDatasource<BoughtData, UUID> accumulatedBoughtDataListDs;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        initDatasource(dailyBoughtDataListDs, mainReportEntryDS.getItem().getDailyBoughtDataList());
        initDatasource(accumulatedBoughtDataListDs, mainReportEntryDS.getItem().getAccumulateBoughtDataList());
    }

    @Override
    protected List<Table> getTablesForInit() {
        List<Table> tablesForInit = new ArrayList<>();
        tablesForInit.add(dailyBoughtDataTable);
        tablesForInit.add(accumulatedBoughDataTable);

        return tablesForInit;
    }
}