package com.pywl.ebangbang_rider.network.service;


import com.pywl.ebangbang_rider.network.entity.CommonEntity;
import com.pywl.ebangbang_rider.network.entity.HomeWaitingForGoodsEntity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by Harry on 2018/8/20.
 */
public interface HomeWaitingForGoodsService {

    @FormUrlEncoded
    @POST
    Observable<HomeWaitingForGoodsEntity> getDataList(@Url String url, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST
    Observable<CommonEntity> arrival(@Url String url, @FieldMap Map<String, String> params);
}
