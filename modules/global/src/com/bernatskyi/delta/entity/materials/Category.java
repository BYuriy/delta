/*
 * Copyright (c) 2015 delta
 */
package com.bernatskyi.delta.entity.materials;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Yuriy
 */
@NamePattern("%s|name")
@Table(name = "DELTA_CATEGORY")
@Entity(name = "delta$Category")
public class Category extends StandardEntity {
    private static final long serialVersionUID = 3040815790588669537L;

    @Column(name = "NAME", nullable = false, length = 150)
    protected String name;




    @OnDelete(DeletePolicy.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SUB_GROUP_ID")
    protected SubGroup subGroup;

    public void setSubGroup(SubGroup subGroup) {
        this.subGroup = subGroup;
    }

    public SubGroup getSubGroup() {
        return subGroup;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}