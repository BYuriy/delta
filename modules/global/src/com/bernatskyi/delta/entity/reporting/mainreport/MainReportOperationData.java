/*
 * Copyright (c) 2016 delta
 */
package com.bernatskyi.delta.entity.reporting.mainreport;

import com.bernatskyi.delta.entity.Storage;
import com.haulmont.chile.core.annotations.MetaClass;
import com.bernatskyi.delta.entity.materials.Category;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.AbstractNotPersistentEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yuriy
 */
@MetaClass(name = "delta$MainReportOperationData")
public class MainReportOperationData extends AbstractNotPersistentEntity {
    private static final long serialVersionUID = 7176789926046075530L;

    @MetaProperty
    protected Category category;

    @MetaProperty
    protected List<StorageStateData> storageStateDataList = new ArrayList<>();

    @MetaProperty
    protected Double summaryVolume;

    @MetaProperty
    protected BigDecimal summaryRealizationPrice;

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public List<StorageStateData> getStorageStateDataList() {
        return storageStateDataList;
    }

    public StorageStateData getStorageStateData(Storage storage) {
        if(storageStateDataList == null) {
            storageStateDataList = new ArrayList<>();
        }

        StorageStateData result = null;
        for(StorageStateData storageStateData : storageStateDataList) {
            if(storageStateData.getStorage().equals(storage)) {
                result = storageStateData;
            }
        }

        if(result == null) {
            result = new StorageStateData();

            result.setStorage(storage);
            result.setVolume(0.0);

            storageStateDataList.add(result);
        }

        return result;
    }

    public void setStorageStateDataList(List<StorageStateData> storageStateDataList) {
        this.storageStateDataList = storageStateDataList;
    }

    public Double getSummaryVolume() {
        return summaryVolume;
    }

    public void setSummaryVolume(Double summaryVolume) {
        this.summaryVolume = summaryVolume;
    }

    public BigDecimal getSummaryRealizationPrice() {
        return summaryRealizationPrice;
    }

    public void setSummaryRealizationPrice(BigDecimal summaryRealizationPrice) {
        this.summaryRealizationPrice = summaryRealizationPrice;
    }
}