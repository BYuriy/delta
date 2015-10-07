/*
 * Copyright (c) 2015 delta
 */
package com.bernatskyi.delta.entity.materials;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.haulmont.cuba.core.entity.StandardEntity;
import javax.persistence.Column;
import com.haulmont.chile.core.annotations.NamePattern;

/**
 * @author User
 */
@NamePattern("%s|name")
@Table(name = "DELTA_GROUP")
@Entity(name = "delta$Group")
public class Group extends StandardEntity {
    private static final long serialVersionUID = 4284456495824653015L;

    @Column(name = "NAME", nullable = false, length = 100)
    protected String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}