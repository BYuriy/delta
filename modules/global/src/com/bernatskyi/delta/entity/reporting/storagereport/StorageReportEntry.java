/*
 * Copyright (c) 2015 delta
 */
package com.bernatskyi.delta.entity.reporting.storagereport;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.cuba.core.entity.AbstractNotPersistentEntity;
import com.bernatskyi.delta.entity.Storage;
import com.haulmont.chile.core.annotations.MetaProperty;
import java.util.Date;
import java.util.List;

/**
 * @author Yuriy
 */
@MetaClass(name = "delta$StorageReportEntry")
public class StorageReportEntry extends AbstractNotPersistentEntity {
    private static final long serialVersionUID = 515621785395734231L;

    @MetaProperty(mandatory = true)
    protected Storage storage;

    @MetaProperty(mandatory = true)
    protected Date date;

    @MetaProperty(mandatory = true)
    protected List<StorageReportCategoryStates> simpleStates;

    public List<StorageReportCategoryStates> getSimpleStates() {
        return simpleStates;
    }

    public void setSimpleStates(List<StorageReportCategoryStates> simpleStates) {
        this.simpleStates = simpleStates;
    }


    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }
}