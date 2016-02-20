/*
 * Copyright (c) 2016 delta
 */
package com.bernatskyi.delta.entity.reporting.mainreport;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.AbstractNotPersistentEntity;

import java.math.BigDecimal;

/**
 * @author Yuriy
 */
@MetaClass(name = "delta$BalanceData")
public class BalanceData extends MainReportOperationData {
    private static final long serialVersionUID = -5496721915032254732L;

    @MetaProperty
    protected BigDecimal summaryPrice;

    @MetaProperty
    protected BigDecimal price;

    @MetaProperty
    protected BigDecimal realizationPrice;

    public BigDecimal getSummaryPrice() {
        return summaryPrice;
    }

    public void setSummaryPrice(BigDecimal summaryPrice) {
        this.summaryPrice = summaryPrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getRealizationPrice() {
        return realizationPrice;
    }

    public void setRealizationPrice(BigDecimal realizationPrice) {
        this.realizationPrice = realizationPrice;
    }
}