package com.pywl.ebangbang_rider.function.completed;

import com.pywl.ebangbang_rider.app_final.URLFinal;
import com.pywl.ebangbang_rider.app_final.UserInfo;
import com.pywl.ebangbang_rider.base.model.BaseModel;
import com.pywl.ebangbang_rider.network.entity.HomeWaitingForGoodsEntity;
import com.pywl.ebangbang_rider.network.service.CompletedService;
import com.pywl.ebangbang_rider.utils.RetrofitHelper;
import com.pywl.ebangbang_rider.utils.SPUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by Harry on 2018/11/12.
 */
public class CompletedModel extends BaseModel {

    private final CompletedService service;

    public CompletedModel() {
        Retrofit retrofit = RetrofitHelper.getInstance().getRetrofit();
        service = retrofit.create(CompletedService.class);
    }

    public void getDataList(Observer<HomeWaitingForGoodsEntity> observer) {
        Map<String, String> params = new HashMap<>();

        params.put("rideId", SPUtils.getString(UserInfo.RIDE_ID.name(), ""));
        params.put("orderFormStatus", "5");//订单已完成

        service.getDataList(URLFinal.GET_WAITING_FOR_GOODS, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
