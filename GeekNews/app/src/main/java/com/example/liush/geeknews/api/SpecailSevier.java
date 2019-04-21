package com.example.liush.geeknews.api;

import com.example.liush.geeknews.bean.DailyBean;
import com.example.liush.geeknews.bean.SpecailBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by liush on 2019/4/18.
 */

public interface SpecailSevier {
    String sBaseUrl = "http://news-at.zhihu.com/api/4/";

    @GET("sections ")
    Observable<SpecailBean> getSpecil();
}
