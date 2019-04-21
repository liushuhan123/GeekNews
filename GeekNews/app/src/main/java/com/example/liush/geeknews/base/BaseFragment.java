package com.example.liush.geeknews.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by liush on 2019/4/3.
 */

public abstract class BaseFragment<V extends BaseView, P extends BasePresenter> extends Fragment implements BaseView {

    private Unbinder mUnbinder;
    public P mMpresneter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(getLayoutId(), null);
        mUnbinder = ButterKnife.bind(this, inflate);
        mMpresneter = initPresenter();
        if (mMpresneter != null) {
            mMpresneter.bind((V) this);
        }
        initView();
        initListener();
        initData();
        return inflate;
    }

    protected void initData() {
    }

    protected void initListener() {
    }

    protected void initView() {

    }

    protected abstract P initPresenter();

    protected abstract int getLayoutId();
}
