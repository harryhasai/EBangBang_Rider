package com.pywl.ebangbang_rider.base.presenter;


import com.pywl.ebangbang_rider.base.view.IBaseView;

/**
 * Created by Harry on 2018/4/17.
 */
public interface IBasePresenter<V extends IBaseView> {

    /**
     * 绑定View层
     * @param view
     */
    void bindView(V view);

    /**
     * 与View层解绑
     */
    void unBindView();

}
