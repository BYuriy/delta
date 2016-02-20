/*
 * Copyright (c) 2016 delta
 */
package com.bernatskyi.delta.entity.reporting.mainreport;

import com.bernatskyi.delta.entity.materials.Category;
import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;

import java.math.BigDecimal;
import java.util.*;

import com.haulmont.cuba.core.entity.AbstractNotPersistentEntity;

/**
 * @author Yuriy
 */
@MetaClass(name = "delta$MainReportEntry")
public class MainReportEntry extends AbstractNotPersistentEntity {
    private static final long serialVersionUID = 2343945039038312561L;

    @MetaProperty
    protected Date date;

    @MetaProperty
    protected List<BoughtData> dailyBoughtDataList = new ArrayList<>();

    protected Map<Category, BoughtData> dailyBoughtDataMap = new HashMap<>();

    @MetaProperty
    protected List<BoughtData> accumulatedBoughtDataList = new ArrayList<>();

    protected Map<Category, BoughtData> accumulatedBoughtDataMap = new HashMap<>();

    @MetaProperty
    protected List<SellData> dalySellDataList = new ArrayList<>();

    protected Map<Category, SellData> dalySellDataMap = new HashMap<>();

    @MetaProperty
    protected List<SellData> accumulatedSellDataList = new ArrayList<>();

    protected Map<Category, SellData> accumulatedSellDataMap = new HashMap<>();

    @MetaProperty
    protected List<BalanceData> balanceDataList = new ArrayList<>();

    protected Map<Category, BalanceData> balanceDataMap = new HashMap<>();

    public MainReportEntry(Date date) {
        this.date = date;
    }

    public MainReportEntry() {
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public List<BoughtData> getDailyBoughtDataList() {
        return dailyBoughtDataList;
    }

    public BoughtData getDailyBoughtData(Category category) {

        BoughtData result = dailyBoughtDataMap.get(category);

        if (result == null) {
            result = new BoughtData();

            result.setCategory(category);
            result.setSummaryPrice(BigDecimal.ZERO);
            result.setSummaryVolume(0.0);
            result.setSummaryRealizationPrice(BigDecimal.ZERO);

            dailyBoughtDataList.add(result);
            dailyBoughtDataMap.put(category, result);
        }

        return result;
    }

    public void setDailyBoughtDataList(List<BoughtData> dalyBoughtDataList) {
        this.dailyBoughtDataList = dalyBoughtDataList;
    }

    public List<BoughtData> getAccumulateBoughtDataList() {
        return accumulatedBoughtDataList;
    }

    public BoughtData getAccumulatedBoughtData(Category category) {

        BoughtData result = accumulatedBoughtDataMap.get(category);

        if (result == null) {
            result = new BoughtData();

            result.setCategory(category);
            result.setSummaryPrice(BigDecimal.ZERO);
            result.setSummaryVolume(0.0);
            result.setSummaryRealizationPrice(BigDecimal.ZERO);

            accumulatedBoughtDataList.add(result);
            accumulatedBoughtDataMap.put(category, result);
        }

        return result;
    }

    public void setAccumulateBoughtDataList(List<BoughtData> accumulateBoughtDataList) {
        this.accumulatedBoughtDataList = accumulateBoughtDataList;
    }

    public List<SellData> getDailySellDataList() {
        return dalySellDataList;
    }

    public SellData getDailySellData(Category category) {
        SellData result = dalySellDataMap.get(category);

        if (result == null) {
            result = new SellData();

            result.setCategory(category);
            result.setSummaryVolume(0.0);
            result.setSummaryRealizationPrice(BigDecimal.ZERO);

            dalySellDataList.add(result);
            dalySellDataMap.put(category, result);
        }

        return result;
    }

    public void setDalySellDataList(List<SellData> dalySellDataList) {
        this.dalySellDataList = dalySellDataList;
    }

    public List<SellData> getAccumulatedSellDataList() {
        return accumulatedSellDataList;
    }

    public SellData getAccumulatedSellData(Category category) {
        SellData result = accumulatedSellDataMap.get(category);

        if (result == null) {
            result = new SellData();

            result.setCategory(category);
            result.setSummaryVolume(0.0);
            result.setSummaryRealizationPrice(BigDecimal.ZERO);

            accumulatedSellDataList.add(result);
            accumulatedSellDataMap.put(category, result);
        }

        return result;
    }

    public void setAccumulatedSellDataList(List<SellData> accumulatedSellDataList) {
        this.accumulatedSellDataList = accumulatedSellDataList;
    }

    public List<BalanceData> getBalanceDataList() {
        return balanceDataList;
    }

    public BalanceData getBalanceData(Category category) {
        BalanceData result = balanceDataMap.get(category);

        if (result == null) {
            result = new BalanceData();

            result.setCategory(category);
            result.setPrice(BigDecimal.ZERO);
            result.setSummaryVolume(0.0);
            result.setSummaryRealizationPrice(BigDecimal.ZERO);

            balanceDataList.add(result);
            balanceDataMap.put(category, result);
        }

        return result;
    }


    public void setBalanceDataList(List<BalanceData> balanceDataList) {
        this.balanceDataList = balanceDataList;
    }
}