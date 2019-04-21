package com.example.liush.geeknews.model;

import com.example.liush.geeknews.api.SpecailSevier;
import com.example.liush.geeknews.base.BaexModel;
import com.example.liush.geeknews.bean.SpecailBean;
import com.example.liush.geeknews.net.CallBack;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by liush on 2019/4/18.
 */

public class SpecialMoedl extends BaexModel {
    public void getData(final CallBack<SpecailBean> callBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SpecailSevier.sBaseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SpecailSevier sevier = retrofit.create(SpecailSevier.class);
        Observable<SpecailBean> specil = sevier.getSpecil();
        specil.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SpecailBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(SpecailBean value) {
                        callBack.onSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
