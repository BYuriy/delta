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
@MetaClass(name = "delta$SellData")
public class SellData extends MainReportOperationData {
    private static final long serialVersionUID = 6448920169555246422L;

    @MetaProperty
    protected BigDecimal realizationPrice;

    public SellData() {
        realizationPrice = BigDecimal.ZERO;
    }

    public BigDecimal getRealizationPrice() {
        return realizationPrice;
    }

    public void setRealizationPrice(BigDecimal realizationPrice) {
        this.realizationPrice = realizationPrice.setScale(2, RoundingMode.HALF_UP);
    }
}