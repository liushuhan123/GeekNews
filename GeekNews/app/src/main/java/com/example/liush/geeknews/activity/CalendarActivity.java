package com.example.liush.geeknews.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.liush.geeknews.R;
import com.example.liush.geeknews.base.BaseActivity;
import com.example.liush.geeknews.bean.CalendarBean;
import com.example.liush.geeknews.presenter.CalendarPresenter;
import com.example.liush.geeknews.view.CalendarView;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


//刘姝晗
public class CalendarActivity extends BaseActivity<CalendarView, CalendarPresenter> implements CalendarView {


    private static final String TAG = "CalendarActivity";
    @BindView(R.id.too)
    Toolbar mToo;

    @BindView(R.id.cal_tv)
    TextView mCalTv;
    int page = 20181206;
    @BindView(R.id.calendarView)
    MaterialCalendarView mCalendarView;
    private int mMonth;
    private int mYear;
    private int mDay;

    @Override
    protected CalendarPresenter initPresenter() {
        return new CalendarPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initData() {
        mMpresenter.getData(page);
    }

    @Override
    protected void initview() {
        mToo.setTitle("选择日期");
        setSupportActionBar(mToo);
    }

    @Override
    protected void initLstener() {
       mCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
           @Override
           public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
               mMonth = date.getMonth();
               mYear = date.getYear();
               mDay = date.getDay();
               Log.d("data", "111: " + mYear + mMonth + mDay);

           }
       });
        mCalTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("year",mYear);
                intent.putExtra("mon",mMonth);
                intent.putExtra("day",mDay);
                setResult(200, intent);
                finish();
            }
        });
    }

    @Override
    public void setData(CalendarBean bean) {
        List<CalendarBean.StoriesBean> stories = bean.getStories();
        Log.d(TAG, "setData: " + stories);

    }


}
