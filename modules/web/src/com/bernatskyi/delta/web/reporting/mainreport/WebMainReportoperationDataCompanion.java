package com.bernatskyi.delta.web.reporting.mainreport;

import com.bernatskyi.delta.entity.Storage;
import com.bernatskyi.delta.entity.reporting.mainreport.BoughtData;
import com.bernatskyi.delta.entity.reporting.mainreport.MainReportOperationData;
import com.bernatskyi.delta.entity.reporting.mainreport.StorageStateData;
import com.bernatskyi.delta.gui.reporting.mainreport.MainReportBoughtDataFrame;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.Datasource;
import com.haulmont.cuba.web.gui.components.WebComponentsHelper;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Created by bernatskyi on 20/02/16.
 */
public class WebMainReportoperationDataCompanion implements MainReportBoughtDataFrame.Companion {
    @Override
    public void initTable(Collection<Storage> storages, Table table) {
        Messages messages = AppBeans.get(Messages.class);
        final String volumeCaption = messages.getMessage("com.bernatskyi.delta.gui.reporting.mainreport", "volume.caption");
        final String summaryPriceCaption = messages.getMessage("com.bernatskyi.delta.gui.reporting.mainreport", "summaryPrice.caption");

        final CollectionDatasource<MainReportOperationData, UUID> datasource = table.getDatasource();

        final DecimalFormat df = new DecimalFormat("# ###.00");

        for(Storage storage : storages) {
            com.vaadin.ui.Table webTable =  WebComponentsHelper.unwrap(table);



            webTable.addGeneratedColumn(String.format("%s %s",storage.getName(), volumeCaption), new com.vaadin.ui.Table.ColumnGenerator() {
                @Override
                public Object generateCell(com.vaadin.ui.Table source, Object itemId, Object columnId) {
                    MainReportOperationData data = datasource.getItem((UUID) itemId);

                    String storageName = columnId.toString().split(" ")[0];

                    if (data != null) {
                        for (StorageStateData storageStateData : data.getStorageStateDataList()) {
                            if (storageStateData.getStorage().getName().equals(storageName)) {
                                return storageStateData.getVolume();
                            }
                        }
                    }

                    return 0.00;
                }
            });


        }
    }
}
