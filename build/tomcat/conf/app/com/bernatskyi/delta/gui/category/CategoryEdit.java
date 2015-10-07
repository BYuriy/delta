/*
 * Copyright (c) 2015 delta
 */
package com.bernatskyi.delta.gui.category;

import com.bernatskyi.delta.entity.materials.SubGroup;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.bernatskyi.delta.entity.materials.Category;
import com.haulmont.cuba.gui.components.Field;

import javax.inject.Named;
import java.util.Map;

/**
 * @author User
 */
public class CategoryEdit extends AbstractEditor<Category> {
    public  static final String SUBGROUP_PARAMETER_NAME = "subGroup";

    private SubGroup subGroup;

    @Named("fieldGroup.subGroup")
    private Field subGroupField;

    @Override
    public void init(Map<String, Object> params) {
        subGroup = (SubGroup) params.get(SUBGROUP_PARAMETER_NAME);

        if(subGroup != null) {
            subGroupField.setVisible(false);
        }
    }

    @Override
    protected void initNewItem(Category item) {
        item.setSubGroup(subGroup);
    }
}