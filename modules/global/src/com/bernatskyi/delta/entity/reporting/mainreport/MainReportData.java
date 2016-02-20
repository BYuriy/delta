/*
 * Copyright (c) 2016 delta
 */
package com.bernatskyi.delta.entity.reporting.mainreport;

import com.bernatskyi.delta.entity.Storage;
import com.bernatskyi.delta.entity.StorageCategoryState;
import com.bernatskyi.delta.entity.materials.Category;
import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.AbstractNotPersistentEntity;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.*;

/**
 * @author Yuriy
 */
@MetaClass(name = "delta$MainReportData")
public class MainReportData extends AbstractNotPersistentEntity {
    private static final long serialVersionUID = 1673000859201962112L;

    protected Map<Key, StorageCategoryState> map;
    protected Map<Date, MainReportEntry> entryMap;

    public MainReportData() {
        map = new HashMap<>();
        entryMap = new HashMap<>();
        mainReportEntryList = new ArrayList<>();
    }

    @MetaProperty
    protected List<MainReportEntry> mainReportEntryList;

    public List<MainReportEntry> getMainReportEntryList() {
        return mainReportEntryList;
    }

    public void setMainReportEntryList(List<MainReportEntry> mainReportEntryList) {
        this.mainReportEntryList = mainReportEntryList;
    }

    public void addMainReportEntry(MainReportEntry entry) {
        if(!entryMap.containsKey(entry.getDate())) {
            entryMap.putIfAbsent(entry.date, entry);
            mainReportEntryList.add(entry);
        }
    }

    public MainReportEntry getMainReportEntryByDate(Date date) {
        return entryMap.containsKey(date) ? entryMap.get(date) : entryMap.put(date, new MainReportEntry());
    }

    public StorageCategoryState getStorageCategoryState(Storage storage, Category category) {
        return map.get(new Key(storage.getId(), category.getId()));
    }

    public void addStorageCategoryState(StorageCategoryState state) {
        map.put(new Key(state.getStorage().getId(), state.getCategory().getId()), state);
    }

    protected class Key {
        private UUID storageId;
        private UUID categoryId;

        public Key(UUID storageId, UUID categoryId) {
            this.storageId = storageId;
            this.categoryId = categoryId;
        }

        public UUID getStorageId() {
            return storageId;
        }

        public void setStorageId(UUID storageId) {
            this.storageId = storageId;
        }

        public UUID getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(UUID categoryId) {
            this.categoryId = categoryId;
        }

        @Override
        public int hashCode() {
           return new HashCodeBuilder(45, 10)
                   .append(storageId)
                   .append(categoryId)
                   .toHashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if(!(obj instanceof Key)) {
                return false;
            }
            if(obj == this) {
                return true;
            }

            Key key = (Key) obj;

            return new EqualsBuilder()
                    .append(storageId, key.getStorageId())
                    .append(categoryId, key.getCategoryId())
                    .isEquals();
        }
    }
}