/*
 * Copyright (c) 2015 delta
 */
package com.bernatskyi.delta.gui.reporting.storage;

import com.bernatskyi.delta.entity.Storage;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.components.actions.BaseAction;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.ValueListener;
import org.apache.commons.collections.CollectionUtils;

import javax.inject.Named;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Yuriy
 */
public class StorageReportParametes extends AbstractWindow {
    @Named("cancellButton")
    private Button cancelButton;

    @Named("generateReportButton")
    private Button generateButton;

    @Named("dateParameterField")
    private DateField dateField;

    @Named("storagesTokenList")
    private TokenList storagesTokenList;

    @Named("allStoragesDs")
    private CollectionDatasource<Storage, UUID> allStoragesDs;

    @Override
    public void init(Map<String, Object> params) {
        cancelButton.setAction(new DialogAction(DialogAction.Type.CANCEL) {
            @Override
            public void actionPerform(Component component) {
                close(Window.CLOSE_ACTION_ID);
            }
        });

        generateButton.setAction(new BaseAction("GENERATE_STORAGE_REPORT_ACTION") {
            @Override
            public void actionPerform(Component component) {
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("date", dateField.getValue());
                parameters.put(
                        "storages", CollectionUtils.isNotEmpty(storagesTokenList.getDatasource().getItems())
                        ? storagesTokenList.getDatasource().getItems()
                        : allStoragesDs.getItems()
                );

                openWindow("delta$StorageReport", WindowManager.OpenType.THIS_TAB, parameters);
            }
        });
//        generateButton.setEnabled(false);

        dateField.setValue(new Date());
        dateField.addListener(new ValueListener() {
            @Override
            public void valueChanged(Object source, String property, Object prevValue, Object value) {
                generateButton.setEnabled(value != null);
            }
        });
    }
}