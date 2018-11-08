package com.pywl.ebangbang_rider.rx;

import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2018/11/6.
 */

public interface RxActionManager<T> {
    void add(T tag, Disposable disposable);

    void remove(T tag);

    void cancel(T tag);

    void cancelAll();
}
