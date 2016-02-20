/*
 * Copyright (c) 2015 delta
 */
package com.bernatskyi.delta.entity.reporting.storagereport;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import java.math.BigDecimal;
import com.haulmont.cuba.core.entity.AbstractNotPersistentEntity;

/**
 * @author Yuriy
 */
@MetaClass(name = "delta$StorageReportCategoryState")
public class StorageReportCategoryState extends AbstractNotPersistentEntity {
    private static final long serialVersionUID = -8111992468926363832L;

    @MetaProperty(mandatory = true)
    protected Double volume;

    @MetaProperty(mandatory = true)
    protected BigDecimal price;

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getVolume() {
        return volume;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }


}