package com.example.liush.geeknews.api;

import com.example.liush.geeknews.bean.DailyBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by liush on 2019/4/17.
 */

public interface DailySevier {
    String sBaseUrl = "http://news-at.zhihu.com/api/4/";

    @GET("news/latest")
    Observable<DailyBean> getLastDailyNews();
}
