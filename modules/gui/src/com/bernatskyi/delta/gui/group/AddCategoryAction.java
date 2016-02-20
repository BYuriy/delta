/*
 * Copyright (c) 2015 delta
 */

package com.bernatskyi.delta.gui.group;

import com.bernatskyi.delta.entity.materials.Category;
import com.bernatskyi.delta.entity.materials.Group;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.components.Window;
import com.haulmont.cuba.gui.components.actions.AddAction;
import com.haulmont.cuba.gui.data.CollectionDatasource;

import java.util.Collection;
import java.util.UUID;

/**
 * Created by Yuriy on 14.12.2015.
 */
public class AddCategoryAction extends AddAction {
    public static final String ACTION_ID = "category.add";

    private CollectionDatasource<Group, UUID> groupsDs;
    private CollectionDatasource<Category, UUID> categoriesDs;

    public AddCategoryAction(CollectionDatasource<Group, UUID> groupsDs, Table target) {
        super(target, null, WindowManager.OpenType.THIS_TAB, ACTION_ID);

        setHandler(new AddCategoryHandler());

        this.groupsDs = groupsDs;
        this.categoriesDs = target.getDatasource();
    }

    @Override
    public boolean isEnabled() {
        return groupsDs.getItem()!= null;
    }

    private class AddCategoryHandler implements Window.Lookup.Handler {
        @Override
        public void handleLookup(Collection items) {
            for (Object object : items) {
                Category category = (Category) object;

                category.setGroup(groupsDs.getItem());

                categoriesDs.includeItem(category);
                categoriesDs.modifyItem(category);
            }

            categoriesDs.commit();
        }
    }
}
