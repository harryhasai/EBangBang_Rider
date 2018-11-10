package com.pywl.ebangbang_rider.function.personal_center.order_taking_manage;

import com.blankj.utilcode.util.ToastUtils;
import com.pywl.ebangbang_rider.app_final.DisposableFinal;
import com.pywl.ebangbang_rider.base.presenter.BasePresenter;
import com.pywl.ebangbang_rider.network.entity.CommonEntity;
import com.pywl.ebangbang_rider.network.entity.OrderTakingManageEntity;
import com.pywl.ebangbang_rider.rx.DisposableManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2018/11/9.
 */
public class OrderTakingManagePresenter extends BasePresenter<OrderTakingManageActivity> {

    private final OrderTakingManageModel model;

    public OrderTakingManagePresenter() {
        model = new OrderTakingManageModel();
    }

    /**
     * @param status 1开始接单 2暂停接单 3关闭接单
     */
    public void save(String status) {
        model.save(status, new Observer<CommonEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.ORDER_TAKING_MANAGE_SAVE, d);
            }

            @Override
            public void onNext(CommonEntity commonEntity) {
                if (commonEntity.code == 1) {
                    view.finish();
                    ToastUtils.showShort("设置成功");
                } else {
                    ToastUtils.showShort(commonEntity.msg);
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

    public void getStatus() {
        model.getStatus(new Observer<OrderTakingManageEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.ORDER_TAKING_MANAGE_GET_STATUS, d);
            }

            @Override
            public void onNext(OrderTakingManageEntity orderTakingManageEntity) {
                if (orderTakingManageEntity.code == 1) {
                    view.initViewStatus(orderTakingManageEntity.status);
                } else {
                    ToastUtils.showShort(orderTakingManageEntity.msg);
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
