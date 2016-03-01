/*
 * Copyright (c) 2016 delta
 */
package com.bernatskyi.delta.entity.reporting.mainreport;

import com.haulmont.chile.core.annotations.MetaClass;
import com.bernatskyi.delta.entity.Storage;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.AbstractNotPersistentEntity;

import java.math.BigDecimal;

/**
 * @author Yuriy
 */
@MetaClass(name = "delta$StorageStateData")
public class StorageStateData extends AbstractNotPersistentEntity {
    private static final long serialVersionUID = -1344944173496769937L;

    @MetaProperty
    protected Storage storage;

    @MetaProperty
    protected Double volume;

    @MetaProperty
    protected BigDecimal summaryPrice;

    public StorageStateData() {
        volume = 0.0;
        summaryPrice = BigDecimal.ZERO;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getVolume() {
        return volume;
    }

    public BigDecimal getSummaryPrice() {
        return summaryPrice;
    }

    public void setSummaryPrice(BigDecimal summaryPrice) {
        this.summaryPrice = summaryPrice;
    }
}