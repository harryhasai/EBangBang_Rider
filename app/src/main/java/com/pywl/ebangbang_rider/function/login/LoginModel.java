package com.pywl.ebangbang_rider.function.login;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.pywl.ebangbang_rider.app_final.URLFinal;
import com.pywl.ebangbang_rider.base.model.BaseModel;
import com.pywl.ebangbang_rider.network.entity.LoginEntity;
import com.pywl.ebangbang_rider.network.service.LoginService;
import com.pywl.ebangbang_rider.utils.RetrofitHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by Harry on 2018/11/6.
 */
public class LoginModel extends BaseModel {

    private final LoginService service;

    public LoginModel() {
        Retrofit retrofit = RetrofitHelper.getInstance().getRetrofit();
        service = retrofit.create(LoginService.class);
    }

    public void login(String userName, String password, Observer<LoginEntity> observer) {
        Map<String, String> params = new HashMap<>();

        params.put("loginName", userName);
        params.put("passWord", password);

        service.login(URLFinal.LOGIN, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}
