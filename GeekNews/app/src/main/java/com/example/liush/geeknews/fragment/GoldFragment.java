package com.example.liush.geeknews.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.liush.geeknews.R;
import com.example.liush.geeknews.activity.ShowActivity;
import com.example.liush.geeknews.adapter.VpGoldAdapter;
import com.example.liush.geeknews.base.BaseFragment;
import com.example.liush.geeknews.bean.GoldBean;
import com.example.liush.geeknews.presenter.GoldPresenter;
import com.example.liush.geeknews.view.GoldView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoldFragment extends BaseFragment<GoldView, GoldPresenter> implements GoldView, View.OnClickListener {


    @BindView(R.id.gold_tab)
    TabLayout mGoldTab;
    @BindView(R.id.gold_vp)
    ViewPager mGoldVp;
    @BindView(R.id.iv)
    ImageView mIv;
    private ArrayList<GoldBean> mTitle;
    private ArrayList<Fragment> mFragments;

    @Override
    protected GoldPresenter initPresenter() {
        return new GoldPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gold;
    }

    @Override
    protected void initView() {
        initTitle();
        initFragment();
    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        for (int i = 0; i < mTitle.size(); i++) {
            GoldBean bean = mTitle.get(i);
            if (bean.isChecked) {
                mFragments.add(GoldDetailFragment.newInstance(bean.title));
            }
        }
        VpGoldAdapter adapter = new VpGoldAdapter(getChildFragmentManager(), mTitle, mFragments);
        mGoldVp.setAdapter(adapter);
        mGoldTab.setupWithViewPager(mGoldVp);

    }

    //tab
    private void initTitle() {
        mTitle = new ArrayList<>();
        mTitle.add(new GoldBean("工具资源", true));
        mTitle.add(new GoldBean("Android", true));
        mTitle.add(new GoldBean("iOS", true));
        mTitle.add(new GoldBean("设计", true));
        mTitle.add(new GoldBean("产品", true));
        mTitle.add(new GoldBean("阅读", true));
        mTitle.add(new GoldBean("前端", true));
        mTitle.add(new GoldBean("后端", true));
    }

    @Override
    protected void initListener() {
        mIv.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), ShowActivity.class);
        intent.putExtra("list", mTitle);
        startActivityForResult(intent, 100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null){
            if (requestCode == 100 && resultCode == 200){
                mTitle = (ArrayList<GoldBean>) data.getSerializableExtra("list");
                //刷新界面
                initFragment();
            }
        }
    }
}
