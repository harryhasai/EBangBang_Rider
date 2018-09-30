package com.pywl.ebangbang_rider.base;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.jaeger.library.StatusBarUtil;
import com.pywl.ebangbang_rider.R;
import com.pywl.ebangbang_rider.application.EBangBangRiderApplication;
import com.pywl.ebangbang_rider.base.presenter.BasePresenter;
import com.pywl.ebangbang_rider.base.view.BaseActivityImpl;

/**
 * Created by Harry on 2018/8/13.
 */
public abstract class BaseActivity<P extends BasePresenter> extends BaseActivityImpl<P> {

    private EBangBangRiderApplication application;
    private AlertDialog dialog;
    protected Bundle savedInstanceState;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.savedInstanceState = savedInstanceState;

        setContentView(setupView());
        application = (EBangBangRiderApplication) getApplication();
        application.addActivity(this);

        //只是手机竖屏显示
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //设置状态栏颜色
        StatusBarUtil.setColor(this, getResources().getColor(R.color.app_status_bar_color), 0);

        initView();

    }

    /**
     * @return 布局文件的ID
     */
    protected abstract int setupView();

    /**
     * 初始化布局(例如findViewById)
     */
    protected abstract void initView();

    private void initDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.LoadingDialog);
        dialog = builder.create();
        dialog.setCancelable(false);
        dialog.setView(View.inflate(this, R.layout.dialog_base, null));
    }

    /**
     * 显示LoadingDialog
     */
    public void showDialog() {
        initDialog();
        dialog.show();
    }

    /**
     * 关闭LoadingDialog
     */
    public void dismissDialog() {
        if (dialog != null) {
            dialog.cancel();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        application.finishActivity(this);   //清除栈中的Activity
    }

}
