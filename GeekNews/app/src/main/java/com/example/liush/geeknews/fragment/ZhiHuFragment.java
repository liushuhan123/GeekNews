package com.example.liush.geeknews.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liush.geeknews.R;
import com.example.liush.geeknews.adapter.RlvDailyAdapter;
import com.example.liush.geeknews.base.BaseFragment;
import com.example.liush.geeknews.presenter.ZhiHuPresenter;
import com.example.liush.geeknews.view.ZhiHuView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZhiHuFragment extends BaseFragment<ZhiHuView, ZhiHuPresenter> implements ZhiHuView {
    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.vp)
    ViewPager mVp;
    private ArrayList<String> mTitle;
    private ArrayList<Fragment> mFragments;

    @Override
    protected ZhiHuPresenter initPresenter() {

        return new ZhiHuPresenter();
    }

    @Override
    protected int getLayoutId() {

        return R.layout.fragment_zhi_hu;
    }

    @Override
    protected void initView() {
        initTitle();
        initFragment();
        RlvDailyAdapter adapter = new RlvDailyAdapter(getChildFragmentManager(), mTitle, mFragments);
        mVp.setAdapter(adapter);
        mTab.setupWithViewPager(mVp);
    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        mFragments.add(new DailyFragment());
        mFragments.add(new ThemeFragment());
        mFragments.add(new SpecialFragment());
        mFragments.add(new HotFragment());

    }

    private void initTitle() {
        mTitle = new ArrayList<>();
        mTitle.add("日报");
        mTitle.add("主题");
        mTitle.add("专栏");
        mTitle.add("热门");
    }
}
