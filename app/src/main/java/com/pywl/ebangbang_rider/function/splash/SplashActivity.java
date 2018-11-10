package com.pywl.ebangbang_rider.function.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.pywl.ebangbang_rider.R;
import com.pywl.ebangbang_rider.app_final.UserInfo;
import com.pywl.ebangbang_rider.function.login.LoginActivity;
import com.pywl.ebangbang_rider.function.main.MainActivity;
import com.pywl.ebangbang_rider.utils.RxPermissionsUtils;
import com.pywl.ebangbang_rider.utils.SPUtils;

/**
 * Created by Harry on 2018/11/9.
 * 闪屏页面
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mCountDownTimer.start();
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
        mCountDownTimer.cancel();
    }
}
