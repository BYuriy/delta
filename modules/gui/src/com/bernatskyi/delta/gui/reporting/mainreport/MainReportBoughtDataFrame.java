/*
 * Copyright (c) 2016 delta
 */
package com.bernatskyi.delta.gui.reporting.mainreport;

import com.bernatskyi.delta.entity.Storage;
import com.bernatskyi.delta.entity.reporting.mainreport.BoughtData;
import com.bernatskyi.delta.entity.reporting.mainreport.StorageStateData;
import com.haulmont.cuba.gui.components.AbstractWindow;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.data.CollectionDatasource;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author Yuriy
 */
public class MainReportBoughtDataFrame extends AbstractWindow {
    @Inject
    Table dailyBoughtDataTable;

    @Inject
    Table accumulatedSellDataTable;

    @Inject
    CollectionDatasource<BoughtData, UUID> dailyBoughtDataListDs;

    @Inject
    CollectionDatasource<BoughtData, UUID> accumulatedBoughtDataListDs;

    @Override
    public void init(Map<String, Object> params) {
        List<Storage> storages  = (List<Storage>) params.get("storages");
        List<BoughtData> dailyBoughDataList = (List<BoughtData>) params.get("dailyBoughtData");
        List<BoughtData> accumulatedBoughtData = (List<BoughtData>) params.get("accumulatedBoughtData");

        dailyBoughtDataListDs.getItems().addAll(dailyBoughDataList);
        accumulatedBoughtDataListDs.getItems().addAll(accumulatedBoughtData);

        initTableGeneratedColumns(storages, dailyBoughtDataTable);
        initTableGeneratedColumns(storages, accumulatedSellDataTable);

        dailyBoughtDataListDs.refresh();
        accumulatedBoughtDataListDs.refresh();
    }

    private void initTableGeneratedColumns(List<Storage> storages, Table table) {
        for(Storage storage : storages) {
            ((com.vaadin.ui.Table)table).addGeneratedColumn(storage.getName() + ".volume", new com.vaadin.ui.Table.ColumnGenerator() {
                @Override
                public Object generateCell(com.vaadin.ui.Table source, Object itemId, Object columnId) {
                    BoughtData boughtData = (BoughtData) ((Table)source).getDatasource().getItem(itemId);

                    String storageName = columnId.toString().split("\\.")[0];

                    for(StorageStateData storageStateData : boughtData.getStorageStateDataList())  {
                        if(storageStateData.getStorage().getName().equals(storageName)) {
                            return storageStateData.getVolume();
                        }
                    }

                    return 0.0;
                }
            });
        }
    }
}