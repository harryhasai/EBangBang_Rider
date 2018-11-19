package com.pywl.ebangbang_rider.function.register;

import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.pywl.ebangbang_rider.R;
import com.pywl.ebangbang_rider.base.BaseActivity;
import com.pywl.ebangbang_rider.base.presenter.BasePresenter;
import com.pywl.ebangbang_rider.function.login.LoginActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2018/9/26.
 * 注册页面
 */
public class RegisterActivity extends BaseActivity {

    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_identifying_code)
    EditText etIdentifyingCode;
    @BindView(R.id.tv_get_identifying_code)
    TextView tvGetIdentifyingCode;
    @BindView(R.id.et_password)
    EditText etPassword;
    /**
     * 倒计时的类
     */
    private CountDownTimer countDownTimer;

    @Override
    protected int setupView() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        return null;
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @OnClick({R.id.tv_get_identifying_code, R.id.btn_register, R.id.tv_login_now})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_get_identifying_code:
                countDown();
                break;
            case R.id.btn_register:
                break;
            case R.id.tv_login_now:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }

    private void countDown() {
        countDownTimer = new CountDownTimer(60 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvGetIdentifyingCode.setText(millisUntilFinished / 1000 + "秒后重新获取");
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
