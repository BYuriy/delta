/*
 * Copyright (c) 2015 delta
 */
package com.bernatskyi.delta.gui.group;

import com.bernatskyi.delta.entity.materials.SubGroup;
import com.bernatskyi.delta.gui.subgroup.SubGroupEdit;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.*;
import com.bernatskyi.delta.entity.materials.Group;
import com.haulmont.cuba.gui.components.actions.CreateAction;
import com.haulmont.cuba.gui.data.Datasource;

import javax.inject.Named;
import java.util.Collections;
import java.util.Map;

/**
 * @author User
 */
public class GroupEdit extends AbstractEditor<Group> {
    @Named("groupDs")
    Datasource<Group> groupDs;

    @Named("createBtn")
    Button createBtn;

    @Named("subGroupsTable")
    Table subGroupsTable;

    @Override
    public void init(Map<String, Object> params) {
        initSubGroupOperationsAction();
    }

    @Override
    protected void postInit() {
        Group group = getItem();

        if(group.getVersion() == null) {
            createBtn.setEnabled(false);
        }
    }

    private void initSubGroupOperationsAction() {
        SubGroupCreateAction createAction = new SubGroupCreateAction(subGroupsTable, "delta$SubGroupCreate");
        subGroupsTable.addAction(createAction);
        createBtn.setAction(createAction);
    }

    private class SubGroupCreateAction extends CreateAction {
        public SubGroupCreateAction(ListComponent target, String id) {
            super(target, WindowManager.OpenType.THIS_TAB, id);
        }

        @Override
        public Map<String, Object> getWindowParams() {
            return Collections.<String, Object>singletonMap(SubGroupEdit.GROUP_PARAMETER_NAME, getItem());
        }
    }
}