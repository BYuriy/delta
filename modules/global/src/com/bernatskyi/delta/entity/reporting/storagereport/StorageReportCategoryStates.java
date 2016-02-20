/*
 * Copyright (c) 2015 delta
 */
package com.bernatskyi.delta.entity.reporting.storagereport;

import com.haulmont.chile.core.annotations.MetaClass;
import com.bernatskyi.delta.entity.materials.Category;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.AbstractNotPersistentEntity;

/**
 * @author Yuriy
 */
@MetaClass(name = "delta$StorageReportCategoryStates")
public class StorageReportCategoryStates extends AbstractNotPersistentEntity {
    private static final long serialVersionUID = 2676103137105755855L;

    @MetaProperty(mandatory = true)
    protected Category category;

    @MetaProperty(mandatory = true)
    protected StorageReportCategoryState stateOnStart;

    @MetaProperty(mandatory = true)
    protected StorageReportCategoryState boughtState;

    @MetaProperty(mandatory = true)
    protected StorageReportCategoryState saledState;

    @MetaProperty(mandatory= true)
    protected StorageReportCategoryState movedFromState;

    @MetaProperty(mandatory = true)
    protected StorageReportCategoryState movedToState;

    @MetaProperty(mandatory = true)
    protected StorageReportCategoryState stateOnEnd;

    public void setStateOnStart(StorageReportCategoryState stateOnStart) {
        this.stateOnStart = stateOnStart;
    }

    public StorageReportCategoryState getStateOnStart() {
        return stateOnStart;
    }

    public void setBoughtState(StorageReportCategoryState boughtState) {
        this.boughtState = boughtState;
    }

    public StorageReportCategoryState getBoughtState() {
        return boughtState;
    }

    public void setSaledState(StorageReportCategoryState saledState) {
        this.saledState = saledState;
    }

    public StorageReportCategoryState getSaledState() {
        return saledState;
    }

    public void setStateOnEnd(StorageReportCategoryState stateOnEnd) {
        this.stateOnEnd = stateOnEnd;
    }

    public StorageReportCategoryState getStateOnEnd() {
        return stateOnEnd;
    }


    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public StorageReportCategoryState getMovedFromState() {
        return movedFromState;
    }

    public void setMovedFromState(StorageReportCategoryState movedFromState) {
        this.movedFromState = movedFromState;
    }

    public StorageReportCategoryState getMovedToState() {
        return movedToState;
    }

    public void setMovedToState(StorageReportCategoryState movedToState) {
        this.movedToState = movedToState;
    }
}