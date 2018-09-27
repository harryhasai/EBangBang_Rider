package com.pywl.ebangbang_rider.function.home_waiting_for_goods;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pywl.ebangbang_rider.network.entity.CommonItem;

import java.util.List;

/**
 * Created by Harry on 2018/9/27.
 */
public class HomeWaitingForGoodsAdapter extends BaseQuickAdapter<CommonItem, BaseViewHolder> {

    public HomeWaitingForGoodsAdapter(int layoutResId, @Nullable List<CommonItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommonItem item) {

    }
}
