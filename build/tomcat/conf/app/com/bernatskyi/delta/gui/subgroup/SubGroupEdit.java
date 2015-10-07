/*
 * Copyright (c) 2015 delta
 */
package com.bernatskyi.delta.gui.subgroup;

import com.bernatskyi.delta.entity.materials.Group;
import com.bernatskyi.delta.gui.category.CategoryEdit;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.app.core.categories.CategoryEditor;
import com.haulmont.cuba.gui.components.*;
import com.bernatskyi.delta.entity.materials.SubGroup;
import com.haulmont.cuba.gui.components.actions.CreateAction;

import javax.inject.Named;
import java.util.Collections;
import java.util.Map;

/**
 * @author User
 */
public class SubGroupEdit extends AbstractEditor<SubGroup> {
    public static final String GROUP_PARAMETER_NAME="group";

    private Group group;

    @Named("fieldGroup.group")
    private Field fieldGroup;

    @Named("categoriesTable")
    private Table categoriesTable;

    @Named("createBtn")
    Button createBtn;

    @Override
    public void init(Map<String, Object> params) {
        group = (Group) params.get(GROUP_PARAMETER_NAME);

        if(group != null) {
            fieldGroup.setVisible(false);
        }

        CategoryCreateAction createAction = new CategoryCreateAction(categoriesTable, "delta$CategoryCreate");
        categoriesTable.addAction(createAction);
        createBtn.setAction(createAction);
    }

    @Override
    protected void postInit() {
        SubGroup subGroup = getItem();

        if(subGroup.getVersion() == null) {
            createBtn.setEnabled(false);
        }
    }

    @Override
    protected void initNewItem(SubGroup item) {
        item.setGroup(group);
    }

    private class CategoryCreateAction extends CreateAction {
        public CategoryCreateAction(ListComponent target, String id) {
            super(target, WindowManager.OpenType.THIS_TAB, id);
        }

        @Override
        public Map<String, Object> getWindowParams() {
            return Collections.<String, Object>singletonMap(CategoryEdit.SUBGROUP_PARAMETER_NAME, getItem());
        }
    }
}