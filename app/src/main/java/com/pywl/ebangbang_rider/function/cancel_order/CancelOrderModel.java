package com.pywl.ebangbang_rider.function.cancel_order;

import com.pywl.ebangbang_rider.app_final.URLFinal;
import com.pywl.ebangbang_rider.app_final.UserInfo;
import com.pywl.ebangbang_rider.base.model.BaseModel;
import com.pywl.ebangbang_rider.network.entity.HomeWaitingForGoodsEntity;
import com.pywl.ebangbang_rider.network.service.CancelOrderService;
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
public class CancelOrderModel extends BaseModel {

    private final CancelOrderService service;

    public CancelOrderModel() {
        Retrofit retrofit = RetrofitHelper.getInstance().getRetrofit();
        service = retrofit.create(CancelOrderService.class);
    }

    public void getDataList(Observer<HomeWaitingForGoodsEntity> observer) {
        Map<String, String> params = new HashMap<>();

        params.put("rideId", SPUtils.getString(UserInfo.RIDE_ID.name(), ""));
        params.put("orderFormStatus", "6");//退换货

        service.getDataList(URLFinal.GET_WAITING_FOR_GOODS, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
