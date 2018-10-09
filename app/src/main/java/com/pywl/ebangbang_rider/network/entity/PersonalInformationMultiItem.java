package com.pywl.ebangbang_rider.network.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by Harry on 2018/10/9.
 */
public class PersonalInformationMultiItem implements MultiItemEntity {

    public static final int TOP = 1;
    public static final int CENTER = 2;
    public static final int BOTTOM = 3;
    private int itemType;

    public PersonalInformationMultiItem(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
