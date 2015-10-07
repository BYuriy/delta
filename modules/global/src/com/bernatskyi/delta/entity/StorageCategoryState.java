/*
 * Copyright (c) 2015 delta
 */
package com.bernatskyi.delta.entity;

import javax.annotation.PostConstruct;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.bernatskyi.delta.entity.materials.Category;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.UserSessionSource;

import javax.persistence.Transient;

/**
 * @author Yuriy
 */
@NamePattern("%s %s|storage,category")
@Table(name = "DELTA_STORAGE_CATEGORY_STATE")
@Entity(name = "delta$StorageCategoryState")
public class StorageCategoryState extends StandardEntity {
    private static final long serialVersionUID = 3084168896327047460L;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "STORAGE_ID")
    protected Storage storage;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CATEGORY_ID")
    protected Category category;

    @Column(name = "VOLUME")
    protected Double volume;

    @Column(name = "SUMMARY_PRICE")
    protected BigDecimal summaryPrice;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_TIME")
    protected Date dateTime;

    @Transient
    @MetaProperty
    protected BigDecimal averagePrice;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    public void setAveragePrice(BigDecimal averagePrice) {
        this.averagePrice = averagePrice;
    }

    public BigDecimal getAveragePrice() {
        if(summaryPrice != null && volume != 0) {
            return summaryPrice.divide(BigDecimal.valueOf(volume), 2, BigDecimal.ROUND_HALF_UP);
        }
        return BigDecimal.ZERO;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Date getDateTime() {
        return dateTime;
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

    public void setSummaryPrice(BigDecimal summaryPrice) {
        this.summaryPrice = summaryPrice;
    }

    public BigDecimal getSummaryPrice() {
        return summaryPrice;
    }

    @PostConstruct
    public void init() {
        AppBeans.get(UserSessionSource.class).getUserSession().getUser();

        if(this.volume == null) {
            this.volume = (double) 0;
        }

        if(this.summaryPrice == null) {
            this.summaryPrice = BigDecimal.ZERO;
        }

        if(this.dateTime == null) {
            this.dateTime = new Date();
        }

        this.averagePrice = this.summaryPrice.divide(BigDecimal.valueOf(this.volume), 2, BigDecimal.ROUND_HALF_UP);
    }
}