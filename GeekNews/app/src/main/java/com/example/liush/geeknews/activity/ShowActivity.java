package com.example.liush.geeknews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.liush.geeknews.R;
import com.example.liush.geeknews.adapter.RlvShowAdapter;
import com.example.liush.geeknews.base.BaseActivity;
import com.example.liush.geeknews.bean.GoldBean;
import com.example.liush.geeknews.presenter.EmtyP;
import com.example.liush.geeknews.view.EmtyV;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowActivity extends BaseActivity<EmtyV, EmtyP> implements EmtyV, View.OnClickListener {


    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(R.id.rlv)
    RecyclerView mRlv;
    private ArrayList<GoldBean> mList;

    @Override
    protected EmtyP initPresenter() {
        return new EmtyP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_show;
    }

    @Override
    protected void initview() {
        mToolBar.setTitle("首页特别展示");
        mToolBar.setNavigationIcon(R.mipmap.ic_close);
        setSupportActionBar(mToolBar);
        mToolBar.setNavigationOnClickListener(this);
        mList = (ArrayList<GoldBean>) getIntent().getSerializableExtra("list");
        mRlv.setLayoutManager(new LinearLayoutManager(this));
        RlvShowAdapter adapter = new RlvShowAdapter(this,mList);
        mRlv.setAdapter(adapter);
        mRlv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.putExtra("list", mList);
        setResult(200, intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("list", mList);
        setResult(200, intent);
        finish();
    }
}
