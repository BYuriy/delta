/*
 * Copyright (c) 2016 delta
 */
package com.bernatskyi.delta.gui.reporting.mainreport;

import com.bernatskyi.delta.entity.reporting.mainreport.BalanceData;
import com.bernatskyi.delta.entity.reporting.mainreport.BoughtData;
import com.haulmont.cuba.gui.components.AbstractWindow;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.data.CollectionDatasource;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author Yuriy
 */
public class MainReportBalanceDataFrame extends OperationDataAbstractFrame<BalanceData> {
    @Inject
    Table balanceDataTable;

    @Inject
    CollectionDatasource<BalanceData, UUID> balanceDataListDs;


    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        initDatasource(balanceDataListDs, mainReportEntryDS.getItem().getBalanceDataList());
    }

    @Override
    protected List<Table> getTablesForInit() {
        return Collections.singletonList(balanceDataTable);
    }
}