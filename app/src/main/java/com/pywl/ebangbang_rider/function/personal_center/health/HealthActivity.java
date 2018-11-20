package com.pywl.ebangbang_rider.function.personal_center.health;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pywl.ebangbang_rider.R;
import com.pywl.ebangbang_rider.app_final.DisposableFinal;
import com.pywl.ebangbang_rider.app_final.UserInfo;
import com.pywl.ebangbang_rider.base.BaseActivity;
import com.pywl.ebangbang_rider.base.presenter.BasePresenter;
import com.pywl.ebangbang_rider.utils.SPUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2018/11/20.
 * 健康证
 */
public class HealthActivity extends BaseActivity<HealthPresenter> {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_health)
    ImageView ivHealth;

    @Override
    protected int setupView() {
        return R.layout.activity_health;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        tvTitle.setText("健康证");
        mPresenter.getHealth();
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.HEALTH_ACTIVITY_GET_HEALTH);
        return tags;
    }

    @Override
    protected HealthPresenter bindPresenter() {
        return new HealthPresenter();
    }

    @OnClick({R.id.ll_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
        }
    }

    public void showHealth(String healthCertificate) {
        Picasso.with(this)
                .load(healthCertificate)
                .error(R.drawable.ic_pic_error)
                //.transform(new PicassoCircleTransform())
                //.resize(ConvertUtils.dp2px(65), ConvertUtils.dp2px(65))
                //.centerCrop()
                .into(ivHealth);
    }
}
