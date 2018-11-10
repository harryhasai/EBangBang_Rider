package com.pywl.ebangbang_rider.function.rules_of_management;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pywl.ebangbang_rider.R;
import com.pywl.ebangbang_rider.base.BaseActivity;
import com.pywl.ebangbang_rider.base.presenter.BasePresenter;

import java.util.ArrayList;

/**
 * Created by Harry on 2018/11/10.
 * 管理细则
 */
public class RulesOfManagementActivity extends BaseActivity {
    @Override
    protected int setupView() {
        return R.layout.activity_rules_of_management;
    }

    @Override
    protected void initView() {
        TextView tvTitle = findViewById(R.id.tv_title);
        LinearLayout ll_back = findViewById(R.id.ll_back);
        tvTitle.setText("管理细则");
        ll_back.setOnClickListener(new View.OnClickListener() {
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
