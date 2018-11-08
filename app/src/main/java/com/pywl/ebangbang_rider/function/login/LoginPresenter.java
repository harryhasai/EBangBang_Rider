package com.pywl.ebangbang_rider.function.login;

import android.content.Intent;

import com.blankj.utilcode.util.ToastUtils;
import com.pywl.ebangbang_rider.app_final.DisposableFinal;
import com.pywl.ebangbang_rider.app_final.UserInfo;
import com.pywl.ebangbang_rider.base.presenter.BasePresenter;
import com.pywl.ebangbang_rider.function.main.MainActivity;
import com.pywl.ebangbang_rider.network.entity.LoginEntity;
import com.pywl.ebangbang_rider.rx.DisposableManager;
import com.pywl.ebangbang_rider.utils.SPUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2018/11/6.
 */
public class LoginPresenter extends BasePresenter<LoginActivity> {

    private final LoginModel model;

    public LoginPresenter() {
        model = new LoginModel();
    }

    public void login(String userName, String password) {
        model.login(userName, password, new Observer<LoginEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.LOGIN_ACTIVITY_LOGIN, d);
            }

            @Override
            public void onNext(LoginEntity loginEntity) {
                if (loginEntity.code == 1) {
                    cacheUserInfo(loginEntity);
                    view.startActivity(new Intent(view, MainActivity.class));
                    view.finish();
                } else {
                    ToastUtils.showShort(loginEntity.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("网络连接错误");
                e.printStackTrace();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * 缓存用户数据
     *
     * @param loginEntity 用户数据
     */
    private void cacheUserInfo(LoginEntity loginEntity) {
        SPUtils.putString(UserInfo.ID.name(), String.valueOf(loginEntity.rideData.id));
        SPUtils.putString(UserInfo.NAME.name(), loginEntity.rideData.name);
        SPUtils.putString(UserInfo.PHONE.name(), loginEntity.rideData.phone);
        SPUtils.putString(UserInfo.HEAD_PORTRAIT.name(), loginEntity.rideData.headPortrait);
        SPUtils.putString(UserInfo.TOKEN.name(), loginEntity.token);
        SPUtils.putString(UserInfo.LONGITUDE.name(), loginEntity.rideData.longitude);
        SPUtils.putString(UserInfo.LATITUDE.name(), loginEntity.rideData.latitude);
    }
}
