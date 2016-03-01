/*
 * Copyright (c) 2016 delta
 */
package com.bernatskyi.delta.entity.reporting.mainreport;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.AbstractNotPersistentEntity;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

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

    public BalanceData() {
        super();

        summaryPrice = BigDecimal.ZERO;
        realizationPrice = BigDecimal.ZERO;
    }

    public BigDecimal getSummaryPrice() {
        return summaryPrice;
    }

    public void setSummaryPrice(BigDecimal summaryPrice) {
        this.summaryPrice = summaryPrice.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getPrice() {
        if(summaryVolume > 0) {
            return summaryPrice.divide(new BigDecimal(summaryVolume), new MathContext(2, RoundingMode.HALF_UP)).setScale(2, RoundingMode.HALF_UP);
        } else {
            return BigDecimal.ZERO;
        }
    }

    public void setPrice(BigDecimal price) {
        this.price = price.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getRealizationPrice() {
        return realizationPrice;
    }

    public void setRealizationPrice(BigDecimal realizationPrice) {
        this.realizationPrice = realizationPrice.setScale(2, RoundingMode.HALF_UP);
    }
}