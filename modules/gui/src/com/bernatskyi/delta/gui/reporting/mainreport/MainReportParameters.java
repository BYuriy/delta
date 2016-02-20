/*
 * Copyright (c) 2016 delta
 */
package com.bernatskyi.delta.gui.reporting.mainreport;

import com.bernatskyi.delta.entity.Storage;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.components.actions.BaseAction;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.ValueListener;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.poi.ss.usermodel.DateUtil;

import javax.inject.Named;
import java.util.*;

/**
 * @author Yuriy
 */
public class MainReportParameters extends AbstractWindow {
    @Named("cancellButton")
    private Button cancelButton;

    @Named("generateReportButton")
    private Button generateButton;

    @Named("calculateFromField")
    private DateField calculateFromField;

    @Named("startDateField")
    private DateField startDateField;

    @Named("endDateField")
    private DateField endDateField;

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

        generateButton.setAction(new BaseAction("GENERATE_MAIN_REPORT_ACTION") {
            @Override
            public void actionPerform(Component component) {
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("calculateFrom", calculateFromField.getValue());
                parameters.put("startDate", calculateFromField.getValue());
                parameters.put("endDate", endDateField.getValue());
                parameters.put(
                        "storages", CollectionUtils.isNotEmpty(storagesTokenList.getDatasource().getItems())
                                ? storagesTokenList.getDatasource().getItems()
                                : allStoragesDs.getItems()
                );

                openWindow("delta$MainReport", WindowManager.OpenType.THIS_TAB, parameters);
            }
        });
//        generateButton.setEnabled(false);

        calculateFromField.setValue(DateUtils.round(new Date(), Calendar.MONTH));
        startDateField.setValue(DateUtils.round(new Date(), Calendar.MONTH));
        endDateField.setValue(new Date());

    }
}