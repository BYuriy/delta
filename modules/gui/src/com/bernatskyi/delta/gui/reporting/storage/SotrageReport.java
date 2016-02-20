/*
 * Copyright (c) 2015 delta
 */
package com.bernatskyi.delta.gui.reporting.storage;

import com.bernatskyi.delta.entity.Storage;
import com.bernatskyi.delta.entity.reporting.storagereport.StorageReportCategoryStates;
import com.bernatskyi.delta.entity.reporting.storagereport.StorageReportEntry;
import com.bernatskyi.delta.gui.main.OperationsTable;
import com.bernatskyi.delta.service.ReportService;
import com.haulmont.chile.core.model.MetaClass;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.View;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.data.ValueListener;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import org.apache.commons.collections.CollectionUtils;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;

/**
 * @author Yuriy
 */
public class SotrageReport extends AbstractWindow {
    private ReportService service = AppBeans.get(ReportService.NAME);

    private Collection<Storage> storages;

    @Inject
    ComponentsFactory componentsFactory;

    @Named("dateField")
    DateField dateField;

    @Inject
    TabSheet mainTabSheet;

    @Override
    public void init(Map<String, Object> params) {
        Date date = (Date) params.get("date");
        storages = new ArrayList<>();
        storages.addAll((Collection<? extends Storage>) params.get("storages"));


        dateField.addListener(new ValueListener() {
            @Override
            public void valueChanged(Object source, String property, Object prevValue, Object value) {
                Collection<StorageReportEntry> reportEntries = service.calculateReportEntries(storages, (Date) value);

                initTabs(reportEntries);
            }
        });

        dateField.setValue(date);
    }

    private void initTabs(Collection<StorageReportEntry> reportEntries) {
        if(!CollectionUtils.isEmpty(mainTabSheet.getTabs())) {
            mainTabSheet.removeAllTabs();
        }

        for (StorageReportEntry entry : reportEntries) {
            TabSheet.Tab tab = mainTabSheet.addTab(entry.getStorage().getName(), openFrame(null, "delta$StorageReportEntryFrame", Collections.<String, Object>singletonMap("data", entry)));

            tab.setCaption(entry.getStorage().getName());
        }
    }
}