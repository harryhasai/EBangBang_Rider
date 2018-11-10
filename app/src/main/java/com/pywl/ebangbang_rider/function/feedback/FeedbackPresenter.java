package com.pywl.ebangbang_rider.function.feedback;

import com.blankj.utilcode.util.ToastUtils;
import com.pywl.ebangbang_rider.app_final.DisposableFinal;
import com.pywl.ebangbang_rider.base.presenter.BasePresenter;
import com.pywl.ebangbang_rider.network.entity.CommonEntity;
import com.pywl.ebangbang_rider.rx.DisposableManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2018/11/10.
 */
public class FeedbackPresenter extends BasePresenter<FeedbackActivity> {

    private final FeedbackModel model;

    public FeedbackPresenter() {
        model = new FeedbackModel();
    }

    public void commit(String content, String picture) {
        model.commit(content, picture, new Observer<CommonEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.FEEDBACK_COMMIT, d);
            }

            @Override
            public void onNext(CommonEntity commonEntity) {
                if (commonEntity.code == 1) {
                    ToastUtils.showShort("提交成功");
                    view.finish();
                } else {
                    ToastUtils.showShort(commonEntity.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("网络连接错误");
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
