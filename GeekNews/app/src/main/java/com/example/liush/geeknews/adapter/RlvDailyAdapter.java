package com.example.liush.geeknews.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.liush.geeknews.bean.DailyBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liush on 2019/4/17.
 */

public class RlvDailyAdapter extends FragmentPagerAdapter {

    private ArrayList<String> mtitle;
    private ArrayList<Fragment> mFragments;

    public RlvDailyAdapter(FragmentManager fm, ArrayList<String> mtitle, ArrayList<Fragment> fragments) {
        super(fm);
        this.mtitle = mtitle;
        mFragments = fragments;
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
        return mtitle.get(position);
    }
}
