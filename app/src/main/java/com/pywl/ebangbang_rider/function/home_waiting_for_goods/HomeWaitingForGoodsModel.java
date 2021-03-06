package com.pywl.ebangbang_rider.function.home_waiting_for_goods;

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
public class HomeWaitingForGoodsModel extends BaseModel {

    private final HomeWaitingForGoodsService service;

    public HomeWaitingForGoodsModel() {
        Retrofit retrofit = RetrofitHelper.getInstance().getRetrofit();
        service = retrofit.create(HomeWaitingForGoodsService.class);
    }

    public void getDataList(Observer<HomeWaitingForGoodsEntity> observer) {
        Map<String, String> params = new HashMap<>();

        params.put("rideId", SPUtils.getString(UserInfo.RIDE_ID.name(), ""));
        params.put("orderFormStatus", "3");//骑手正在前往商家

        service.getDataList(URLFinal.GET_WAITING_FOR_GOODS, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void arrival(String orderID, Observer<CommonEntity> observer) {
        Map<String, String> params = new HashMap<>();

        params.put("orderFormId", orderID);

        service.arrival(URLFinal.ARRIVAL, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
