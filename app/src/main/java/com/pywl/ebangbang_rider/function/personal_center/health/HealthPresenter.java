package com.pywl.ebangbang_rider.function.personal_center.health;

import com.blankj.utilcode.util.ToastUtils;
import com.pywl.ebangbang_rider.app_final.DisposableFinal;
import com.pywl.ebangbang_rider.base.presenter.BasePresenter;
import com.pywl.ebangbang_rider.network.entity.HealthEntity;
import com.pywl.ebangbang_rider.rx.DisposableManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2018/11/20.
 */
public class HealthPresenter extends BasePresenter<HealthActivity> {

    private final HealthModel model;

    public HealthPresenter() {
        model = new HealthModel();
    }

    public void getHealth() {
        model.login(new Observer<HealthEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.HEALTH_ACTIVITY_GET_HEALTH, d);
            }

            @Override
            public void onNext(HealthEntity healthEntity) {
                if (healthEntity.code == 1) {
                    view.showHealth(healthEntity.healthCertificate);
                } else {
                    ToastUtils.showShort(healthEntity.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("网络连接错误");
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
