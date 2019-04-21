package com.example.liush.geeknews.model;

import com.example.liush.geeknews.api.HotSevier;
import com.example.liush.geeknews.base.BaexModel;
import com.example.liush.geeknews.bean.HotBean;
import com.example.liush.geeknews.net.CallBack;
import com.example.liush.geeknews.view.HotView;

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

public class HotModel extends BaexModel {
    public void getData(final CallBack<HotBean> callBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HotSevier.sBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        HotSevier sevier = retrofit.create(HotSevier.class);
        Observable<HotBean> hot = sevier.getHot();
        hot.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HotBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(HotBean value) {
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
