package com.pywl.ebangbang_rider.function.personal_center.health;

import com.pywl.ebangbang_rider.app_final.URLFinal;
import com.pywl.ebangbang_rider.app_final.UserInfo;
import com.pywl.ebangbang_rider.base.model.BaseModel;
import com.pywl.ebangbang_rider.network.entity.HealthEntity;
import com.pywl.ebangbang_rider.network.entity.LoginEntity;
import com.pywl.ebangbang_rider.network.service.HealthService;
import com.pywl.ebangbang_rider.utils.RetrofitHelper;
import com.pywl.ebangbang_rider.utils.SPUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by Harry on 2018/11/20.
 */
public class HealthModel extends BaseModel {

    private final HealthService service;

    public HealthModel() {
        Retrofit retrofit = RetrofitHelper.getInstance().getRetrofit();
        service = retrofit.create(HealthService.class);
    }

    public void login(Observer<HealthEntity> observer) {
        Map<String, String> params = new HashMap<>();

        params.put("rideId", SPUtils.getString(UserInfo.RIDE_ID.name(), ""));
        
        service.getHealth(URLFinal.HEALTH, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
