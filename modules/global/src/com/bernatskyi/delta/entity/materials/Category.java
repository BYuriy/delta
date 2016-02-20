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
import com.haulmont.cuba.core.entity.annotation.OnDeleteInverse;
import java.math.BigDecimal;

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

    @OnDeleteInverse(DeletePolicy.DENY)
    @OnDelete(DeletePolicy.UNLINK)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GROUP_ID")
    protected Group group;

    @Column(name = "RELIAZIATION_PRICE")
    protected BigDecimal reliaziationPrice;

    public void setReliaziationPrice(BigDecimal reliaziationPrice) {
        this.reliaziationPrice = reliaziationPrice;
    }

    public BigDecimal getReliaziationPrice() {
        return reliaziationPrice;
    }


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