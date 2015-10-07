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
@Table(name = "DELTA_SUB_GROUP")
@Entity(name = "delta$SubGroup")
public class SubGroup extends StandardEntity {
    private static final long serialVersionUID = -6420325207986603270L;

    @Column(name = "NAME", nullable = false, length = 100)
    protected String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GROUP_ID")
    protected Group group;

    public void setGroup(Group group) {
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}