/*
 * Copyright (c) 2015 delta
 */
package com.bernatskyi.delta.entity.materials;

import javax.persistence.*;

import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.entity.annotation.OnDeleteInverse;
import com.haulmont.cuba.core.global.DeletePolicy;

import java.util.Set;
import com.haulmont.chile.core.annotations.NamePattern;

/**
 * @author Yuriy
 */
@NamePattern("%s|name")
@Table(name = "DELTA_GROUP")
@Entity(name = "delta$Group")
public class Group extends StandardEntity {
    private static final long serialVersionUID = 6465420152813909516L;
    @Column(name = "NAME", nullable = false, length = 100)
    protected String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    protected Group parent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
    @OnDelete(DeletePolicy.DENY)
    @Composition
    protected Set<Category> categories;

    public void setParent(Group parent) {
        this.parent = parent;
    }

    public Group getParent() {
        return parent;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}