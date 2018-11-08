package com.pywl.ebangbang_rider.function.data_setting;

import android.view.View;
import android.widget.TextView;

import com.pywl.ebangbang_rider.R;
import com.pywl.ebangbang_rider.base.BaseActivity;
import com.pywl.ebangbang_rider.base.presenter.BasePresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2018/10/8.
 * 资料设置页面
 */
public class DataSettingActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected int setupView() {
        return R.layout.activity_data_setting;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        tvTitle.setText("设置");
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        return null;
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @OnClick({R.id.ll_back, R.id.tv_title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_title:
                break;
        }
    }
}
