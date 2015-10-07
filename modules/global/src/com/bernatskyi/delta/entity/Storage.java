/*
 * Copyright (c) 2015 delta
 */
package com.bernatskyi.delta.entity;

import javax.persistence.*;

import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;

import java.util.List;

/**
 * @author Yuriy
 */
@NamePattern("%s|name")
@Table(name = "DELTA_STORAGE")
@Entity(name = "delta$Storage")
public class Storage extends StandardEntity {
    private static final long serialVersionUID = -1774348193849831790L;

    @Column(name = "NAME", nullable = false, length = 100)
    protected String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "storage")
    @OnDelete(DeletePolicy.CASCADE)
    @Composition
    protected List<StorageCategoryState> states;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}