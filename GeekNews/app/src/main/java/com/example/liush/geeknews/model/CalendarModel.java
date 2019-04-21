package com.example.liush.geeknews.model;

import com.example.liush.geeknews.api.CalendarSevier;
import com.example.liush.geeknews.base.BaexModel;
import com.example.liush.geeknews.bean.CalendarBean;
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

public class CalendarModel extends BaexModel {
    public void getData(final CallBack<CalendarBean> back, int page) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CalendarSevier.sBaseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CalendarSevier sevier = retrofit.create(CalendarSevier.class);
        Observable<CalendarBean> hot = sevier.getHot(page);
        hot.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CalendarBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(CalendarBean value) {
                        back.onSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        back.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
