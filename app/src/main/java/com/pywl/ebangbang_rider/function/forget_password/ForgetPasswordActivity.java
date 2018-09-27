package com.pywl.ebangbang_rider.function.forget_password;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.pywl.ebangbang_rider.R;
import com.pywl.ebangbang_rider.base.BaseActivity;
import com.pywl.ebangbang_rider.base.presenter.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2018/9/26.
 * 忘记密码
 */
public class ForgetPasswordActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_identifying_code)
    EditText etIdentifyingCode;
    @BindView(R.id.tv_get_identifying_code)
    TextView tvGetIdentifyingCode;
    @BindView(R.id.et_password)
    EditText etPassword;
    /**
     * 倒计时
     */
    private CountDownTimer countDownTimer;

    @Override
    protected int setupView() {
        return R.layout.activity_forget_password;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        tvTitle.setText("找回密码");
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @OnClick({R.id.ll_back, R.id.tv_get_identifying_code, R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_get_identifying_code:
                countDown();
                break;
            case R.id.btn_commit:
                break;
        }
    }

    private void countDown() {
        countDownTimer = new CountDownTimer(60 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvGetIdentifyingCode.setText(millisUntilFinished / 1000 + "后重新获取");
                tvGetIdentifyingCode.setClickable(false);
            }

            @Override
            public void onFinish() {
                tvGetIdentifyingCode.setText("获取验证码");
                tvGetIdentifyingCode.setClickable(true);
            }
        };
        countDownTimer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
