/*
 * Copyright (c) 2016 delta
 */
package com.bernatskyi.delta.entity.reporting.mainreport;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.AbstractNotPersistentEntity;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Yuriy
 */
@MetaClass(name = "delta$BoughtData")
public class BoughtData extends MainReportOperationData {
    private static final long serialVersionUID = 8382551183663494993L;

    @MetaProperty
    protected BigDecimal summaryPrice;

    public BoughtData() {
        setSummaryPrice(BigDecimal.ZERO);
    }

    public BigDecimal getSummaryPrice() {
        return summaryPrice;
    }

    public void setSummaryPrice(BigDecimal summaryPrice) {
        this.summaryPrice = summaryPrice.setScale(2, RoundingMode.HALF_UP);
    }
}