package com.pywl.ebangbang_rider.function.personal_information;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pywl.ebangbang_rider.network.entity.CommonItem;

import java.util.List;

/**
 * Created by Harry on 2018/10/8.
 */
public class PersonalInformationReviewsAdapter extends BaseQuickAdapter<CommonItem, BaseViewHolder> {

    public PersonalInformationReviewsAdapter(int layoutResId, @Nullable List<CommonItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommonItem item) {

    }
}
