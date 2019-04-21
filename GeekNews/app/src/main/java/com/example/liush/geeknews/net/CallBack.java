package com.example.liush.geeknews.net;

/**
 * Created by liush on 2019/4/17.
 */

public interface CallBack<T> {
    void onSuccess(T bean);

    void onFail(String msg);
}
