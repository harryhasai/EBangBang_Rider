package com.pywl.ebangbang_rider.function.personal_center.order_taking_manage;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.pywl.ebangbang_rider.R;
import com.pywl.ebangbang_rider.app_final.DisposableFinal;
import com.pywl.ebangbang_rider.base.BaseActivity;
import com.pywl.ebangbang_rider.base.presenter.BasePresenter;
import com.ruffian.library.RTextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2018/11/9.
 * 接单设置的页面
 */
public class OrderTakingManageActivity extends BaseActivity<OrderTakingManagePresenter> {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_start)
    RTextView tvStart;
    @BindView(R.id.tv_pause)
    RTextView tvPause;
    @BindView(R.id.tv_end)
    RTextView tvEnd;
    private int mStatus = 0;

    @Override
    protected int setupView() {
        return R.layout.activity_order_taking_manage;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        tvTitle.setText("接单设置");

        mPresenter.getStatus();
    }

    /**
     * 初始化控件状态
     * @param status 1开始接单 2暂停接单 3关闭接单
     */
    public void initViewStatus(int status) {
        switch (status) {
            case 1:
                tvStart.setIconNormal(getResources().getDrawable(R.drawable.ic_radio_selected));
                break;
            case 2:
                tvPause.setIconNormal(getResources().getDrawable(R.drawable.ic_radio_selected));
                break;
            case 3:
                tvEnd.setIconNormal(getResources().getDrawable(R.drawable.ic_radio_selected));
                break;
        }
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.ORDER_TAKING_MANAGE_SAVE);
        tags.add(DisposableFinal.ORDER_TAKING_MANAGE_GET_STATUS);
        return tags;
    }

    @Override
    protected OrderTakingManagePresenter bindPresenter() {
        return new OrderTakingManagePresenter();
    }

    @OnClick({R.id.ll_back, R.id.tv_start, R.id.tv_pause, R.id.tv_end, R.id.btn_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_start://开工
                changeStatus(1);
                break;
            case R.id.tv_pause://暂停
                changeStatus(2);
                break;
            case R.id.tv_end://收工
                changeStatus(3);
                break;
            case R.id.btn_save://保存
                if (mStatus != 0) {
                    mPresenter.save(String.valueOf(mStatus));
                }
                break;
        }
    }

    /**
     * @param status 1开始接单 2暂停接单 3关闭接单
     */
    public void changeStatus(int status) {
        switch (status) {
            case 1:
                tvStart.setIconNormal(getResources().getDrawable(R.drawable.ic_radio_selected));
                tvPause.setIconNormal(getResources().getDrawable(R.drawable.ic_radio_unselected));
                tvEnd.setIconNormal(getResources().getDrawable(R.drawable.ic_radio_unselected));
                mStatus = 1;
                break;
            case 2:
                tvStart.setIconNormal(getResources().getDrawable(R.drawable.ic_radio_unselected));
                tvPause.setIconNormal(getResources().getDrawable(R.drawable.ic_radio_selected));
                tvEnd.setIconNormal(getResources().getDrawable(R.drawable.ic_radio_unselected));
                mStatus = 2;
                break;
            case 3:
                tvStart.setIconNormal(getResources().getDrawable(R.drawable.ic_radio_unselected));
                tvPause.setIconNormal(getResources().getDrawable(R.drawable.ic_radio_unselected));
                tvEnd.setIconNormal(getResources().getDrawable(R.drawable.ic_radio_selected));
                mStatus = 3;
                break;
        }
    }
}
