package com.example.liush.geeknews.base;

import java.util.ArrayList;

/**
 * Created by liush on 2019/4/3.
 */

public abstract class BasePresenter<V extends BaseView> {

    protected V mView;

    protected ArrayList<BaexModel> mModels = new ArrayList<>();

    public void bind(V view) {

        mView = view;
    }

    public BasePresenter() {

        initModel();
    }


    protected abstract void initModel();

    public void Destroy() {
        mView = null;
    }

//    public abstract void getData();
}
