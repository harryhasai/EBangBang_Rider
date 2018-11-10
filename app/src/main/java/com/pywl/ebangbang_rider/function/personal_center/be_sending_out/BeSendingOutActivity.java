package com.pywl.ebangbang_rider.function.personal_center.be_sending_out;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pywl.ebangbang_rider.R;
import com.pywl.ebangbang_rider.base.BaseActivity;
import com.pywl.ebangbang_rider.base.presenter.BasePresenter;

import java.util.ArrayList;

/**
 * Created by Harry on 2018/11/10.
 * 正在派送中
 */
public class BeSendingOutActivity extends BaseActivity {

    @Override
    protected int setupView() {
        return R.layout.activity_be_sending_out;
    }

    @Override
    protected void initView() {
        LinearLayout llBack = findViewById(R.id.ll_back);
        TextView tvTitle = findViewById(R.id.tv_title);
        tvTitle.setText("待配送");
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        return null;
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }
}
