/*
 * Copyright (c) 2016 delta
 */
package com.bernatskyi.delta.gui.reporting.mainreport;

import com.bernatskyi.delta.entity.Storage;
import com.bernatskyi.delta.entity.reporting.mainreport.MainReportData;
import com.bernatskyi.delta.entity.reporting.mainreport.MainReportEntry;
import com.bernatskyi.delta.service.ReportService;
import com.haulmont.cuba.gui.components.AbstractWindow;
import com.haulmont.cuba.gui.components.TabSheet;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.Datasource;
import org.apache.commons.lang.time.DateUtils;

import javax.inject.Inject;
import javax.inject.Named;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Yuriy
 */
public class MainReport extends AbstractWindow {
    private MainReportData mainReportData;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM");

    private Collection<Storage> storages;

    @Named("mainTabSheet")
    TabSheet mainTabSheet;

    @Inject
    CollectionDatasource<Storage, UUID> storagesDs;

    @Inject
    ReportService reportService;

    @Inject
    Datasource<MainReportData> mainReportDataDS;

    @Override
    public void init(Map<String, Object> params) {
        Date calculateFrom = (Date) params.get("calculateFrom");
        Date startDate = (Date) params.get("startDate");
        Date endDate = (Date) params.get("endDate");
        storages = new ArrayList<>();
        storages.addAll((Collection<? extends Storage>) params.get("storages"));

        for(Storage storage : storages) {
            storagesDs.includeItem(storage);
        }

        MainReportData mainReportData = reportService.calculateMainReport(storages, calculateFrom, startDate, endDate);
        mainReportDataDS.setItem(mainReportData);

        Date current = startDate;
        while(current.before(endDate)) {
            HashMap<String, Object> parameters = new HashMap<>();
            MainReportEntry entry = mainReportData.getMainReportEntryByDate(current);
            parameters.put("data", entry);

            TabSheet.Tab tab = mainTabSheet.addTab(dateFormat.format(current),
                    openFrame(null, "delta$MainReportEntryFrame", parameters));
            tab.setCaption(dateFormat.format(current));

            current = DateUtils.addDays(current, 1);
        }
    }
}