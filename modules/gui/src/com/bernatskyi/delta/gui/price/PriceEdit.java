/*
 * Copyright (c) 2016 delta
 */
package com.bernatskyi.delta.gui.price;

import com.bernatskyi.delta.entity.materials.Category;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.bernatskyi.delta.entity.Price;

import java.util.Date;
import java.util.Map;

/**
 * @author Yuriy
 */
public class PriceEdit extends AbstractEditor<Price> {
    private Category category;

    @Override
    public void init(Map<String, Object> params) {
        category = (Category) params.get("Category");
    }

    @Override
    protected void initNewItem(Price item) {
        item.setStartDate(new Date());
        item.setCategory(category);
    }
}