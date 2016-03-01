/*
 * Copyright (c) 2016 delta
 */

package com.bernatskyi.delta.gui.reporting.mainreport;

import com.bernatskyi.delta.entity.Storage;
import com.bernatskyi.delta.entity.reporting.mainreport.BoughtData;
import com.bernatskyi.delta.entity.reporting.mainreport.MainReportEntry;
import com.bernatskyi.delta.entity.reporting.mainreport.MainReportOperationData;
import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.Datasource;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Yuriy on 22.02.2016.
 */
public abstract class OperationDataAbstractFrame<T extends MainReportOperationData> extends AbstractFrame {
    public interface Companion {
        void initTable(Collection<Storage> storages, Table table);
    }

    protected CollectionDatasource<Storage, UUID> storagesDs;

    protected Datasource<MainReportEntry> mainReportEntryDS;

    @Override
    public void init(Map<String, Object> params) {
        Companion companion = getCompanion();

        storagesDs = getDsContext().getParent().getParent().get("storagesDs");
        mainReportEntryDS = getDsContext().getParent().get("mainReportEntryDS");

        for(Table table : getTablesForInit()) {
            companion.initTable(storagesDs.getItems(), table);
        }
    }

    protected void initDatasource(CollectionDatasource<T, UUID> datasource, List<T> dataList) {
        for(T data : dataList) {
            datasource.includeItem(data);
        }
    }

    protected abstract List<Table> getTablesForInit();
}
