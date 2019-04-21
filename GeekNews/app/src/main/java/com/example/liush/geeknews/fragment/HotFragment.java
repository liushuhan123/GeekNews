package com.example.liush.geeknews.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liush.geeknews.R;
import com.example.liush.geeknews.adapter.RlvHotAdapter;
import com.example.liush.geeknews.base.BaseFragment;
import com.example.liush.geeknews.bean.HotBean;
import com.example.liush.geeknews.presenter.HotPresenter;
import com.example.liush.geeknews.view.HotView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotFragment extends BaseFragment<HotView, HotPresenter> implements HotView {


    @BindView(R.id.hot_rec)
    RecyclerView mHotRec;
    private ArrayList<HotBean.RecentBean> mBeans;
    private RlvHotAdapter mAdapter;

    @Override
    protected HotPresenter initPresenter() {
        return new HotPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initData() {
        mMpresneter.getData();
    }

    @Override
    protected void initView() {
        mHotRec.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBeans = new ArrayList<>();
        mAdapter = new RlvHotAdapter(getActivity(), mBeans);
        mHotRec.setAdapter(mAdapter);
    }


    @Override
    public void setData(HotBean bean) {
        mBeans.addAll(bean.getRecent());
        mAdapter.notifyDataSetChanged();

    }
}
