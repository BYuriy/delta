/*
 * Copyright (c) 2016 delta
 */
package com.bernatskyi.delta.entity.reporting.mainreport;

import com.haulmont.chile.core.annotations.MetaClass;
import com.bernatskyi.delta.entity.materials.Category;
import com.haulmont.chile.core.annotations.MetaProperty;
import java.math.BigDecimal;
import com.haulmont.cuba.core.entity.AbstractNotPersistentEntity;

/**
 * @author Yuriy
 */
@MetaClass(name = "delta$CategoryRealizationPrice")
public class CategoryRealizationPrice extends AbstractNotPersistentEntity {
    private static final long serialVersionUID = -6046684740504936532L;

    @MetaProperty
    protected Category category;

    @MetaProperty
    protected BigDecimal realizationPrice;

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setRealizationPrice(BigDecimal realizationPrice) {
        this.realizationPrice = realizationPrice;
    }

    public BigDecimal getRealizationPrice() {
        return realizationPrice;
    }


}