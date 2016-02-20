/*
 * Copyright (c) 2016 delta
 */
package com.bernatskyi.delta.gui.reporting.mainreport;

import com.bernatskyi.delta.entity.reporting.mainreport.MainReportEntry;
import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.data.Datasource;

import javax.inject.Inject;
import java.util.Map;

/**
 * @author Yuriy
 */
public class MainReportEntryFrame extends AbstractFrame {
    @Inject
    Datasource<MainReportEntry> mainReportEntryDS;

    @Override
    public void init(Map<String, Object> params) {
        mainReportEntryDS.setItem((MainReportEntry) params.get("data"));
    }
}