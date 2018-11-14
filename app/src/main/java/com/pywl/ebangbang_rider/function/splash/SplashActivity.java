package com.pywl.ebangbang_rider.function.splash;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.pywl.ebangbang_rider.R;
import com.pywl.ebangbang_rider.app_final.UserInfo;
import com.pywl.ebangbang_rider.function.login.LoginActivity;
import com.pywl.ebangbang_rider.function.main.MainActivity;
import com.pywl.ebangbang_rider.utils.SPUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Harry on 2018/11/9.
 * 闪屏页面
 */
public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "SplashActivity";
    private Disposable mDisposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        registerPermission();
    }

    private void registerPermission() {
        RxPermissions rxPermissions = new RxPermissions(this);
        mDisposable = rxPermissions.request(Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            //当所有权限都允许之后，返回true
                            mCountDownTimer.start();
                        } else {
                            //只要有一个权限禁止，返回false，
                            //下一次申请只申请没通过申请的权限
                            finish();
                        }
                    }
                });
    }


    CountDownTimer mCountDownTimer = new CountDownTimer(3 * 500, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            boolean isLogin = SPUtils.getBoolean(UserInfo.IS_LOGIN.name(), false);
            if (isLogin) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            } else {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            }
            finish();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
        mCountDownTimer.cancel();
    }
}
