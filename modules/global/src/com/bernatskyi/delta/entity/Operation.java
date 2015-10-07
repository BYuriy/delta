/*
 * Copyright (c) 2015 delta
 */
package com.bernatskyi.delta.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.bernatskyi.delta.entity.materials.Category;
import com.haulmont.cuba.core.entity.StandardEntity;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.haulmont.chile.core.annotations.NamePattern;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Yuriy
 */
@NamePattern("%s %s %s %s|type,dateTime,storage,category")
@Table(name = "DELTA_OPERATION")
@Entity(name = "delta$Operation")
public class Operation extends StandardEntity {
    private static final long serialVersionUID = -8606578827090100880L;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "STORAGE_ID")
    protected Storage storage;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CATEGORY_ID")
    protected Category category;

    @Column(name = "VOLUME", nullable = false)
    protected Double volume;

    @Column(name = "SUMMARY_PRICE")
    protected BigDecimal summaryPrice;

    @Column(name = "TYPE_", nullable = false)
    protected Integer type;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_TIME", nullable = false)
    protected Date dateTime;

    @Column(name = "DESCRIPTION", length = 150)
    protected String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORAGE_DESTINATION_ID")
    protected Storage destination;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    public void setDestination(Storage destination) {
        this.destination = destination;
    }

    public Storage getDestination() {
        return destination;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }


    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Date getDateTime() {
        return dateTime;
    }


    public void setType(OperationType type) {
        this.type = type == null ? null : type.getId();
    }

    public OperationType getType() {
        return type == null ? null : OperationType.fromId(type);
    }


    public void setSummaryPrice(BigDecimal summaryPrice) {
        this.summaryPrice = summaryPrice;
    }

    public BigDecimal getSummaryPrice() {
        return summaryPrice;
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


}