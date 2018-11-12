package com.pywl.ebangbang_rider.function.main;

import com.pywl.ebangbang_rider.app_final.URLFinal;
import com.pywl.ebangbang_rider.app_final.UserInfo;
import com.pywl.ebangbang_rider.base.model.BaseModel;
import com.pywl.ebangbang_rider.network.entity.CommonEntity;
import com.pywl.ebangbang_rider.network.service.MainService;
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
public class MainModel extends BaseModel {

    private final MainService service;

    public MainModel() {
        Retrofit retrofit = RetrofitHelper.getInstance().getRetrofit();
        service = retrofit.create(MainService.class);
    }

    public void postAddress(String longitude, String latitude, Observer<CommonEntity> observer) {
        Map<String, String> params = new HashMap<>();

        params.put("rideId", SPUtils.getString(UserInfo.RIDE_ID.name(), ""));
        params.put("longitude", longitude);
        params.put("latitude", latitude);

        service.postAddress(URLFinal.UPDATA_RIDE_SITE, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
