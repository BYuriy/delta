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
import com.haulmont.cuba.gui.data.Datasource;
import org.apache.commons.lang.time.DateUtils;

import javax.inject.Inject;
import javax.inject.Named;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

/**
 * @author Yuriy
 */
public class MainReport extends AbstractWindow {
    private MainReportData mainReportData;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM");

    @Named("mainTabSheet")
    TabSheet mainTabSheet;

    @Inject
    ReportService reportService;

    @Inject
    Datasource<MainReportData> mainReportDataDS;

    @Override
    public void init(Map<String, Object> params) {
        Date calculateFrom = (Date) params.get("calculateFrom");
        Date startDate = (Date) params.get("startDate");
        Date endDate = (Date) params.get("endDate");
        Collection<Storage> storages = (Collection<Storage>) params.get("storages");

        MainReportData mainReportData = reportService.calculateMainReport(storages, calculateFrom, startDate, endDate);
        mainReportDataDS.setItem(mainReportData);

        Date current = startDate;
        while(current.before(endDate)) {
            TabSheet.Tab tab = mainTabSheet.addTab(dateFormat.format(current),
                    openFrame(null, "delta$MainReportEntryFrame", Collections.singletonMap("data", (Object) mainReportData.getMainReportEntryByDate(current))));
            tab.setCaption(dateFormat.format(current));

            current = DateUtils.addDays(current, 1);
        }


    }
}