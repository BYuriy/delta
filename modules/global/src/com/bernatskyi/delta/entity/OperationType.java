/*
 * Copyright (c) 2015 delta
 */
package com.bernatskyi.delta.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

/**
 * @author Yuriy
 */
public enum OperationType implements EnumClass<Integer>{

    BUY(0, +1),
    SELL(1, -1),
    MOVE(2, -1),
    SORT(3, +1),
    SURPLUS(4, +1),
    SHORTFALL(5, -1),
    REFUND(6, +1),
    EDIT(7, +1);

    private Integer id;
    private int multiplier;

    OperationType (Integer value, int multiplier) {
        this.id = value;
        this.multiplier = multiplier;
    }

    public Integer getId() {
        return id;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public static OperationType fromId(Integer id) {
        for (OperationType at : OperationType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}