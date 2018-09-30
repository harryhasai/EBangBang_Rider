package com.pywl.ebangbang_rider.function.home_new_task;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pywl.ebangbang_rider.R;
import com.pywl.ebangbang_rider.network.entity.CommonItem;

import java.util.List;

/**
 * Created by Harry on 2018/9/27.
 */
public class HomeNewTaskAdapter extends BaseQuickAdapter<CommonItem, BaseViewHolder> {

    private Activity mActivity;

    public HomeNewTaskAdapter(int layoutResId, @Nullable List<CommonItem> data, Activity activity) {
        super(layoutResId, data);
        this.mActivity = activity;
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
        TextView tvType = helper.getView(R.id.tv_type);
        Button btnAction = helper.getView(R.id.btn_action);
        if (helper.getAdapterPosition() == 1) {
            tvType.setText("系统派单");
            tvType.setTextColor(mActivity.getResources().getColor(R.color.app_status_bar_color));
            btnAction.setText("收到");
            btnAction.setTextColor(mActivity.getResources().getColor(R.color.white));
            btnAction.setBackground(mActivity.getResources().getDrawable(R.drawable.shape_home_new_task_button));
        } else {
            tvType.setText("手动接单");
            tvType.setTextColor(mActivity.getResources().getColor(R.color.black));
            btnAction.setText("抢单");
            btnAction.setTextColor(mActivity.getResources().getColor(R.color.black));
            btnAction.setBackground(mActivity.getResources().getDrawable(R.drawable.shape_round_rectangle_18dp));
        }
    }
}
