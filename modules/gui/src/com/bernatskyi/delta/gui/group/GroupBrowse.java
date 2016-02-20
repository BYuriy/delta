/*
 * Copyright (c) 2015 delta
 */
package com.bernatskyi.delta.gui.group;

import com.bernatskyi.delta.entity.materials.Category;
import com.bernatskyi.delta.entity.materials.Group;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.components.actions.AddAction;
import com.haulmont.cuba.gui.components.actions.CreateAction;
import com.haulmont.cuba.gui.components.actions.EditAction;
import com.haulmont.cuba.gui.components.actions.ExcludeAction;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.Datasource;
import com.haulmont.cuba.gui.data.HierarchicalDatasource;
import com.haulmont.cuba.gui.data.impl.CollectionDsListenerAdapter;

import javax.inject.Named;
import java.util.*;

/**
 * @author User
 */
public class GroupBrowse extends AbstractLookup {
    @Named("addCategoryBtn")
    private Button addCategoryBtn;

    @Named("excludeCategoryBtn")
    private Button excludeCategoryBtn;

    @Named("categoriesDs")
    private CollectionDatasource<Category, UUID> categoriesDs;

    @Named("groupsDs")
    private HierarchicalDatasource<Group, UUID> groupsDs;

    @Named("categoriesTable")
    private Table categoriesTable;

    @Named("groupsTable")
    private Table groupsTable;

    @Override
    public void init(Map<String, Object> params) {
        initCategoriesTable();

        initGroupsTable();
    }

    private void initGroupsTable() {
        groupsTable.addAction(new CreateAction(groupsTable){
            @Override
            public WindowManager.OpenType getOpenType() {
                return WindowManager.OpenType.DIALOG;
            }
        });

        groupsTable.addAction(new EditAction(groupsTable){
            @Override
            public WindowManager.OpenType getOpenType() {
                return WindowManager.OpenType.DIALOG;
            }

            @Override
            protected void afterCommit(Entity entity) {
                groupsTable.refresh();
            }
        });
    }

    private void initCategoriesTable() {
        addCategoryBtn.setAction(new AddAction(categoriesTable, new Handler() {
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
        }));
        addCategoryBtn.setEnabled(false);

        excludeCategoryBtn.setAction(new ExcludeAction(categoriesTable) {
            @Override
            protected void doRemove(Set selected, boolean autocommit) {
                for (Object object : selected) {
                    Category category = (Category) object;

                    category.setGroup(null);

                    categoriesDs.modifyItem(category);
                }

                super.doRemove(selected, autocommit);

                categoriesDs.commit();
            }
        });
        excludeCategoryBtn.setEnabled(false);

        groupsDs.addListener(new CollectionDsListenerAdapter<Group>() {
            @Override
            public void itemChanged(Datasource<Group> ds, Group prevItem, Group item) {
                addCategoryBtn.setEnabled(item != null);
            }
        });

        categoriesDs.addListener(new CollectionDsListenerAdapter<Category>() {
            @Override
            public void itemChanged(Datasource<Category> ds, Category prevItem, Category item) {
                excludeCategoryBtn.setEnabled(item != null);
            }
        });
    }
}