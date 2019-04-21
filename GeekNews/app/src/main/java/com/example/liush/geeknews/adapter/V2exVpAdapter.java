package com.example.liush.geeknews.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.liush.geeknews.bean.V2exTabBean;

import java.util.ArrayList;

/**
 * Created by liush on 2019/4/21.
 */

public class V2exVpAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> mFragments;
    private ArrayList<V2exTabBean> mBeans;

    public V2exVpAdapter(FragmentManager fm, ArrayList<Fragment> fragments, ArrayList<V2exTabBean> beans) {
        super(fm);
        mFragments = fragments;
        mBeans = beans;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mBeans.get(position).tab;
    }
}
