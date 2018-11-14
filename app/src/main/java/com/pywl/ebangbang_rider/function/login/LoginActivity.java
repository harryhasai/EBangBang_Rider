package com.pywl.ebangbang_rider.function.login;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.blankj.utilcode.util.ToastUtils;
import com.pywl.ebangbang_rider.R;
import com.pywl.ebangbang_rider.app_final.DisposableFinal;
import com.pywl.ebangbang_rider.base.BaseActivity;
import com.pywl.ebangbang_rider.function.forget_password.ForgetPasswordActivity;
import com.pywl.ebangbang_rider.function.register.RegisterActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2018/9/26.
 * 登录页面
 */
public class LoginActivity extends BaseActivity<LoginPresenter> {

    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_password)
    EditText etPassword;

    @Override
    protected int setupView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.LOGIN_ACTIVITY_LOGIN);
        return tags;
    }

    @Override
    protected LoginPresenter bindPresenter() {
        return new LoginPresenter();
    }

    @OnClick({R.id.btn_login, R.id.tv_register_now, R.id.tv_forget_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                String phone = etPhone.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)) {
                    ToastUtils.showShort("账号或者密码不能为空");
                    return;
                }
                mPresenter.login(phone, password);
                break;
            case R.id.tv_register_now:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.tv_forget_password:
                startActivity(new Intent(this, ForgetPasswordActivity.class));
                break;
        }
    }
}
