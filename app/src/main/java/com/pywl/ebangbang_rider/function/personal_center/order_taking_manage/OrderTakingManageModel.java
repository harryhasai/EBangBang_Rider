package com.pywl.ebangbang_rider.function.personal_center.order_taking_manage;

import com.pywl.ebangbang_rider.app_final.URLFinal;
import com.pywl.ebangbang_rider.app_final.UserInfo;
import com.pywl.ebangbang_rider.base.model.BaseModel;
import com.pywl.ebangbang_rider.network.entity.CommonEntity;
import com.pywl.ebangbang_rider.network.entity.OrderTakingManageEntity;
import com.pywl.ebangbang_rider.network.service.OrderTakingManageService;
import com.pywl.ebangbang_rider.utils.RetrofitHelper;
import com.pywl.ebangbang_rider.utils.SPUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by Harry on 2018/11/9.
 */
public class OrderTakingManageModel extends BaseModel {

    private final OrderTakingManageService service;

    public OrderTakingManageModel() {
        Retrofit retrofit = RetrofitHelper.getInstance().getRetrofit();
        service = retrofit.create(OrderTakingManageService.class);
    }

    public void save(String status, Observer<CommonEntity> observer) {
        Map<String, String> params = new HashMap<>();
        params.put("rideId", SPUtils.getString(UserInfo.RIDE_ID.name(), ""));
        params.put("status", status);//1开始接单 2暂停接单 3关闭接单

        service.save(URLFinal.ORDER_TAKING_MANAGE_SAVE, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getStatus(Observer<OrderTakingManageEntity> observer) {
        Map<String, String> params = new HashMap<>();
        params.put("rideId", SPUtils.getString(UserInfo.RIDE_ID.name(), ""));

        service.getStatus(URLFinal.ORDER_TAKING_MANAGE_GET_STATUS, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
