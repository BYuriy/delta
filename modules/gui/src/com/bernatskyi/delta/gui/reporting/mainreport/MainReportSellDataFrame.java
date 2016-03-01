/*
 * Copyright (c) 2016 delta
 */
package com.bernatskyi.delta.gui.reporting.mainreport;

import com.bernatskyi.delta.entity.reporting.mainreport.BoughtData;
import com.bernatskyi.delta.entity.reporting.mainreport.SellData;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.data.CollectionDatasource;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author Yuriy
 */
public class MainReportSellDataFrame extends OperationDataAbstractFrame<SellData> {
    @Inject
    Table dailySellDataTable;

    @Inject
    Table accumulatedSellDataTable;

    @Inject
    CollectionDatasource<SellData, UUID> dailySellDataListDs;

    @Inject
    CollectionDatasource<SellData, UUID> accumulatedSellDataListDs;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        initDatasource(dailySellDataListDs, mainReportEntryDS.getItem().getDailySellDataList());
        initDatasource(accumulatedSellDataListDs, mainReportEntryDS.getItem().getAccumulatedSellDataList());
    }

    @Override
    protected List<Table> getTablesForInit() {
        ArrayList<Table> tablesForInit = new ArrayList<>();
        tablesForInit.add(dailySellDataTable);
        tablesForInit.add(accumulatedSellDataTable);

        return tablesForInit;
    }
}