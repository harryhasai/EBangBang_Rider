package com.pywl.ebangbang_rider.function.main;

import com.blankj.utilcode.util.ToastUtils;
import com.pywl.ebangbang_rider.app_final.DisposableFinal;
import com.pywl.ebangbang_rider.base.presenter.BasePresenter;
import com.pywl.ebangbang_rider.network.entity.CommonEntity;
import com.pywl.ebangbang_rider.rx.DisposableManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2018/11/12.
 */
public class MainPresenter extends BasePresenter<MainActivity> {

    private final MainModel model;

    public MainPresenter() {
        model = new MainModel();
    }

    public void postAddress(String longitude, String latitude) {
        model.postAddress(longitude, latitude, new Observer<CommonEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.MAIN_ACTIVITY_POST_ADDRESS, d);
            }

            @Override
            public void onNext(CommonEntity commonEntity) {

            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("网络错误, 发送位置信息失败");
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
