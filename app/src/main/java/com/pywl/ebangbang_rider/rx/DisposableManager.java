package com.pywl.ebangbang_rider.rx;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.ArrayMap;

import java.util.Set;

import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2018/11/6.
 * RxJava里的方法, 最用是切断管道, 以免在页面异常退出或者过快退出的时候还在请求网络, 造成空指针
 */

public class DisposableManager implements RxActionManager<Object> {
    private static DisposableManager sInstance = null;

    private ArrayMap<Object, Disposable> maps;

    public static DisposableManager get() {

        if (sInstance == null) {
            synchronized (DisposableManager.class) {
                if (sInstance == null) {
                    sInstance = new DisposableManager();
                }
            }
        }
        return sInstance;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private DisposableManager() {
        maps = new ArrayMap<>();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void add(Object tag, Disposable disposable) {
        if (maps.get(tag) == null) {
            maps.put(tag, disposable);
        }
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void remove(Object tag) {
        if (!maps.isEmpty()) {
            maps.remove(tag);
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void removeAll() {
        if (!maps.isEmpty()) {
            maps.clear();
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void cancel(Object tag) {
        if (maps.isEmpty()) {
            return;
        }
        if (maps.get(tag) == null) {
            return;
        }
        if (!maps.get(tag).isDisposed()) {
            maps.get(tag).dispose();
            maps.remove(tag);

        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void cancelAll() {
        if (maps.isEmpty()) {
            return;
        }
        Set<Object> keys = maps.keySet();
        for (Object apiKey : keys) {
            cancel(apiKey);
        }
    }
}

