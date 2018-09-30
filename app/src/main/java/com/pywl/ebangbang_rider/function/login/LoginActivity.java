package com.pywl.ebangbang_rider.function.login;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.pywl.ebangbang_rider.R;
import com.pywl.ebangbang_rider.app_final.ConstantFinal;
import com.pywl.ebangbang_rider.base.BaseActivity;
import com.pywl.ebangbang_rider.base.presenter.BasePresenter;
import com.pywl.ebangbang_rider.function.forget_password.ForgetPasswordActivity;
import com.pywl.ebangbang_rider.function.main.MainActivity;
import com.pywl.ebangbang_rider.function.register.RegisterActivity;
import com.pywl.ebangbang_rider.utils.RxPermissionsUtils;
import com.pywl.ebangbang_rider.utils.SPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2018/9/26.
 * 登录页面
 */
public class LoginActivity extends BaseActivity {

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

        registerPermission();
    }

    /**
     * 提示用户注册app相关的权限
     */
    private void registerPermission() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("系统需要注册相关用户权限来提高您的用户体验")
                .setCancelable(false)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        for (String aPermissionArray : RxPermissionsUtils.permissionArray) {
                            boolean isRegister = RxPermissionsUtils.checkPermissions(LoginActivity.this, aPermissionArray);
                            if (!isRegister) {
                                RxPermissionsUtils.registerPermissions(LoginActivity.this);
                            }
                        }
                    }
                }).show();
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @OnClick({R.id.btn_login, R.id.tv_register_now, R.id.tv_forget_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                startActivity(new Intent(this, MainActivity.class));
                finish();
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
