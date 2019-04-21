package com.example.liush.geeknews.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.liush.geeknews.R;
import com.example.liush.geeknews.activity.CalendarActivity;
import com.example.liush.geeknews.adapter.RlvDailyAdapter1;
import com.example.liush.geeknews.base.BaseFragment;
import com.example.liush.geeknews.bean.DailyBean;
import com.example.liush.geeknews.presenter.DailyPresenter;
import com.example.liush.geeknews.view.DailyView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class DailyFragment extends BaseFragment<DailyView, DailyPresenter> implements DailyView {


    @BindView(R.id.daily_rec)
    RecyclerView mDailyRec;
    @BindView(R.id.pink_icon)
    com.getbase.floatingactionbutton.FloatingActionButton mPinkIcon;

    private ArrayList<DailyBean.StoriesBean> mStoriesBeans;
    private ArrayList<DailyBean.TopStoriesBean> mTop;

    @Override
    protected DailyPresenter initPresenter() {
        return new DailyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_daily;
    }

    @Override
    protected void initView() {
        mDailyRec.setLayoutManager(new LinearLayoutManager(getContext()));
        mStoriesBeans = new ArrayList<>();
        mTop = new ArrayList<>();

    }

    @Override
    protected void initData() {
        mMpresneter.getData();
    }

    @Override
    public void setData(DailyBean bean) {
        mTop.clear();
        if (bean.getTop_stories().size() > 0) {
            mTop.addAll(bean.getTop_stories());
        }
        mStoriesBeans.clear();
        if (bean.getStories().size() > 0) {
            mStoriesBeans.addAll(bean.getStories());
        }
        RlvDailyAdapter1 adapter1 = new RlvDailyAdapter1(getContext(), mStoriesBeans, mTop);
        mDailyRec.setAdapter(adapter1);
    }

    @OnClick(R.id.pink_icon)
    public void onViewClicked() {
        mPinkIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CalendarActivity.class);
                startActivityForResult(intent, 100);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 200) {
                String year = data.getStringExtra("year");
                String mon = data.getStringExtra("mon");
                String day = data.getStringExtra("day");
                Log.d("data", "onActivityResult: " + year + mon + day);
        }
    }
}
