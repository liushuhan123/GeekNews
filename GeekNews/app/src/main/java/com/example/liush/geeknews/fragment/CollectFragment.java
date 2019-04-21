package com.example.liush.geeknews.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liush.geeknews.R;
import com.example.liush.geeknews.base.BaseFragment;
import com.example.liush.geeknews.presenter.CollectPresenter;
import com.example.liush.geeknews.view.CollectView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectFragment extends BaseFragment<CollectView, CollectPresenter> implements CollectView {


    @Override
    protected CollectPresenter initPresenter() {
        return new CollectPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_collect;
    }
}
