package com.example.liush.geeknews.base;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by liush on 2019/4/3.
 */

public class BaexModel {
    protected CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public void onDestroy() {
        mCompositeDisposable.clear();
    }
}
