package com.example.liush.geeknews.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liush.geeknews.R;
import com.example.liush.geeknews.adapter.RlvSpecilAdapter;
import com.example.liush.geeknews.base.BaseFragment;
import com.example.liush.geeknews.bean.SpecailBean;
import com.example.liush.geeknews.presenter.SpecialPresnetr;
import com.example.liush.geeknews.view.SpecialView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class SpecialFragment extends BaseFragment<SpecialView, SpecialPresnetr> implements SpecialView {


    @BindView(R.id.specil_rel)
    RecyclerView mSpecilRel;
    private ArrayList<SpecailBean.DataBean> mBeans;

    @Override
    protected SpecialPresnetr initPresenter() {
        return new SpecialPresnetr();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_special;
    }

    @Override
    protected void initView() {
        mSpecilRel.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mBeans = new ArrayList<>();
    }

    @Override
    protected void initData() {
        mMpresneter.getData();
    }

    @Override
    public void setData(SpecailBean bean) {
        mBeans.addAll(bean.getData());
        RlvSpecilAdapter adapter = new RlvSpecilAdapter(getContext(), mBeans);
        mSpecilRel.setAdapter(adapter);
    }
}
