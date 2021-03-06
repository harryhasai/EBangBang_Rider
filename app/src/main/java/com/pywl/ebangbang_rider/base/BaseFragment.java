package com.pywl.ebangbang_rider.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pywl.ebangbang_rider.R;
import com.pywl.ebangbang_rider.base.presenter.BasePresenter;
import com.pywl.ebangbang_rider.base.view.BaseFragmentImpl;
import com.pywl.ebangbang_rider.rx.DisposableManager;

import java.util.ArrayList;

/**
 * Created by Harry on 2018/8/13.
 */
public abstract class BaseFragment<P extends BasePresenter> extends BaseFragmentImpl<P> {

    protected Activity mActivity;
    private View view;
    private AlertDialog dialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mActivity = getActivity();
        if (view == null) {
            view = inflater.inflate(setupView(), container, false);
            initView(view);
        }

        //判断Fragment对应的Activity是否存在这个视图
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            //如果存在,那么我就干掉,重写添加,这样的方式我们就可以缓存视图
            parent.removeView(view);
        }

        return view;
    }

    /**
     * @return 布局文件的ID
     */
    protected abstract int setupView();

    /**
     * 初始化布局(例如findViewById)
     *
     * @param view
     */
    protected abstract void initView(View view);

    private void initDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity, R.style.LoadingDialog);
        dialog = builder.create();
        dialog.setCancelable(false);
        dialog.setView(View.inflate(mActivity, R.layout.dialog_base, null));
    }

    /**
     * @return RxJava中的Disposable方法
     */
    protected abstract ArrayList<Object> cancelNetWork();

    /**
     * 显示LoadingDialog
     */
    public void showDialog() {
        initDialog();
        dialog.show();
    }

    /**
     * 关闭LoadingDialog
     */
    public void dismissDialog() {
        if (dialog != null) {
            dialog.cancel();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        ArrayList<Object> tags = cancelNetWork();
        if (tags != null && tags.size() != 0) {
            for (Object tag : tags) {
                DisposableManager.get().cancel(tag);
            }
        }
    }
}
