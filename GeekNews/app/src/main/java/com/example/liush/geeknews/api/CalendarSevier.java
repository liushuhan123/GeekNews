package com.example.liush.geeknews.api;

import com.example.liush.geeknews.bean.CalendarBean;
import com.example.liush.geeknews.bean.HotBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by liush on 2019/4/18.
 */

public interface CalendarSevier {
    String sBaseUrl = "http://news-at.zhihu.com/api/4/";

    @GET("news/before/{date}")
    Observable<CalendarBean> getHot(@Path("date") int date);
}
