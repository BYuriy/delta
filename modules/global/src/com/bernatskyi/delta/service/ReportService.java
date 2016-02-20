/*
 * Copyright (c) 2015 delta
 */
package com.bernatskyi.delta.service;

import com.bernatskyi.delta.entity.Storage;
import com.bernatskyi.delta.entity.reporting.mainreport.MainReportData;
import com.bernatskyi.delta.entity.reporting.storagereport.StorageReportEntry;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author Yuriy
 */
public interface ReportService {
    String NAME = "delta_ReportService";

    Collection<StorageReportEntry> calculateReportEntries(Collection<Storage> storage, Date date);

    MainReportData calculateMainReport(Collection<Storage> storages, Date calculateFrom, Date startDate, Date endDate);
}