package com.pywl.ebangbang_rider.function.home_waiting_for_goods;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pywl.ebangbang_rider.R;
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
        final ImageView ivArrow = helper.getView(R.id.iv_arrow);
        final LinearLayout llMore = helper.getView(R.id.ll_more);
        ivArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (llMore.getVisibility() == View.GONE) {
                    llMore.setVisibility(View.VISIBLE);
                    ivArrow.setRotation(90);
                } else {
                    llMore.setVisibility(View.GONE);
                    ivArrow.setRotation(0);
                }
            }
        });
    }
}
