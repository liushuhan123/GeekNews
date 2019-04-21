package com.example.liush.geeknews.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.liush.geeknews.bean.GoldBean;

import java.util.ArrayList;

/**
 * Created by liush on 2019/4/19.
 */

public class VpGoldAdapter extends FragmentStatePagerAdapter {
    private ArrayList<GoldBean> mtitle;
    private ArrayList<Fragment> mFragments;
    private ArrayList<String> mNewTitles = new ArrayList<>();

    public VpGoldAdapter(FragmentManager fm, ArrayList<GoldBean> mtitle, ArrayList<Fragment> fragments) {
        super(fm);
        this.mtitle = mtitle;
        mFragments = fragments;
        //如果是true状态
        for (int i = 0; i < mtitle.size(); i++) {
            GoldBean bean = mtitle.get(i);
            if (bean.isChecked) {
                mNewTitles.add(bean.title);
            }
        }
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
        return mNewTitles.get(position);
    }
}
