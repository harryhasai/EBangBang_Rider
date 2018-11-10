package com.pywl.ebangbang_rider.function.feedback;

import android.text.TextUtils;

import com.pywl.ebangbang_rider.app_final.URLFinal;
import com.pywl.ebangbang_rider.app_final.UserInfo;
import com.pywl.ebangbang_rider.base.model.BaseModel;
import com.pywl.ebangbang_rider.network.entity.CommonEntity;
import com.pywl.ebangbang_rider.network.service.FeedbackService;
import com.pywl.ebangbang_rider.utils.RetrofitHelper;
import com.pywl.ebangbang_rider.utils.SPUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by Harry on 2018/11/10.
 */
public class FeedbackModel extends BaseModel {

    private final FeedbackService service;

    public FeedbackModel() {
        Retrofit retrofit = RetrofitHelper.getInstance().getRetrofit();
        service = retrofit.create(FeedbackService.class);
    }

    public void commit(String content, String picture, Observer<CommonEntity> observer) {
        Map<String, String> params = new HashMap<>();
        params.put("userId", SPUtils.getString(UserInfo.USER_ID.name(), ""));
        params.put("type", "2");//0 客户端 1 商家端 2 骑手端
        params.put("content", content);
        if (!TextUtils.isEmpty(picture)) {
            params.put("picture", picture);
        }

        service.commit(URLFinal.FEEDBACK_COMMIT, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
