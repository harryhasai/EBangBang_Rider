package com.pywl.ebangbang_rider.function.home_be_sending_out;

import com.pywl.ebangbang_rider.app_final.URLFinal;
import com.pywl.ebangbang_rider.app_final.UserInfo;
import com.pywl.ebangbang_rider.base.model.BaseModel;
import com.pywl.ebangbang_rider.network.entity.CommonEntity;
import com.pywl.ebangbang_rider.network.entity.HomeWaitingForGoodsEntity;
import com.pywl.ebangbang_rider.network.service.HomeWaitingForGoodsService;
import com.pywl.ebangbang_rider.utils.RetrofitHelper;
import com.pywl.ebangbang_rider.utils.SPUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by Harry on 2018/11/8.
 */
public class HomeBeSendingOutModel extends BaseModel {

    private final HomeWaitingForGoodsService service;

    public HomeBeSendingOutModel() {
        Retrofit retrofit = RetrofitHelper.getInstance().getRetrofit();
        service = retrofit.create(HomeWaitingForGoodsService.class);
    }

    public void getDataList(Observer<HomeWaitingForGoodsEntity> observer) {
        Map<String, String> params = new HashMap<>();

        params.put("rideId", SPUtils.getString(UserInfo.RIDE_ID.name(), ""));
        params.put("orderFormStatus", "4");//骑手配送中

        service.getDataList(URLFinal.GET_WAITING_FOR_GOODS, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void complete(String orderFormId, Observer<CommonEntity> observer) {
        Map<String, String> params = new HashMap<>();

        params.put("rideId", SPUtils.getString(UserInfo.RIDE_ID.name(), ""));
        params.put("orderFormId", orderFormId);//骑手配送的订单ID

        service.complete(URLFinal.COMPLETE, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
