package com.example.liush.geeknews.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by liush on 2019/4/3.
 */

public abstract class BaseActivity<V extends BaseView, P extends BasePresenter> extends AppCompatActivity implements BaseView {

    protected P mMpresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //布局
        setContentView(getLayoutId());
        //绑定
        ButterKnife.bind(this);
        //判断是否有p层
        mMpresenter = initPresenter();
        if (mMpresenter != null) {
            //如果有数据去绑定
            mMpresenter.bind((V) this);
        }
        initview();
        initLstener();
        initData();

    }

    protected void initLstener() {
    }

    protected void initview() {
    }

    protected void initData() {

    }

    ;

    protected abstract P initPresenter();

    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMpresenter.Destroy();
        mMpresenter = null;
    }
}
