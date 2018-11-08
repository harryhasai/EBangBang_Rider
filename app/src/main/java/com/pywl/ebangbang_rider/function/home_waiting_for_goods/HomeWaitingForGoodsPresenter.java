package com.pywl.ebangbang_rider.function.home_waiting_for_goods;

import com.blankj.utilcode.util.ToastUtils;
import com.pywl.ebangbang_rider.app_final.DisposableFinal;
import com.pywl.ebangbang_rider.base.presenter.BasePresenter;
import com.pywl.ebangbang_rider.network.entity.HomeWaitingForGoodsEntity;
import com.pywl.ebangbang_rider.rx.DisposableManager;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2018/11/8.
 */
public class HomeWaitingForGoodsPresenter extends BasePresenter<HomeWaitingForGoodsFragment> {

    private final HomeWaitingForGoodsModel model;

    public HomeWaitingForGoodsPresenter() {
        model = new HomeWaitingForGoodsModel();
    }

    public void getDataList() {
        model.getDataList(new Observer<HomeWaitingForGoodsEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.HOME_WAITING_FOR_GOODS_FRAGMENT_GET_DATA_LIST, d);
            }

            @Override
            public void onNext(HomeWaitingForGoodsEntity homeWaitingForGoodsEntity) {
                if (homeWaitingForGoodsEntity.code == 1) {
                    List<HomeWaitingForGoodsEntity.DataBean> data = homeWaitingForGoodsEntity.data;
                    if (data.size() != 0) {
                        view.getDataList(data);
                    } else {
                        ToastUtils.showShort("暂无待取货数据");
                    }
                } else {
                    ToastUtils.showShort(homeWaitingForGoodsEntity.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("网络连接错误");
                view.stopRefresh();
            }

            @Override
            public void onComplete() {
                view.stopRefresh();
            }
        });
    }
}
