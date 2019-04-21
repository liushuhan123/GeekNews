package com.example.liush.geeknews.model;

import com.example.liush.geeknews.api.DailySevier;
import com.example.liush.geeknews.base.BaexModel;
import com.example.liush.geeknews.bean.DailyBean;
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
 * Created by liush on 2019/4/17.
 */

public class DailyModel extends BaexModel {
    public void getData(final CallBack<DailyBean> callBack) {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(DailySevier.sBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        DailySevier sevier = build.create(DailySevier.class);
        Observable<DailyBean> news = sevier.getLastDailyNews();
        news.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DailyBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(DailyBean value) {
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


