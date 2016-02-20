/*
 * Copyright (c) 2016 delta
 */
package com.bernatskyi.delta.entity.reporting.mainreport;

import com.haulmont.chile.core.annotations.MetaClass;
import com.bernatskyi.delta.entity.Storage;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.AbstractNotPersistentEntity;

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


}