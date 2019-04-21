package com.example.liush.geeknews.base;

import android.app.Application;

/**
 * Created by liush on 2019/4/17.
 */

public class BaseApp extends Application {
    public static BaseApp baseApp;

    @Override
    public void onCreate() {
        super.onCreate();
        baseApp = this;
    }

    public static BaseApp getBaseApp() {
        return baseApp;
    }
}
