package com.example.liush.geeknews.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.liush.geeknews.R;
import com.example.liush.geeknews.base.BaseFragment;
import com.example.liush.geeknews.presenter.EmtyP;
import com.example.liush.geeknews.view.EmtyV;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoldDetailFragment extends BaseFragment<EmtyV, EmtyP> implements EmtyV {


    @BindView(R.id.date_tv)
    TextView mDateTv;

    public static GoldDetailFragment newInstance(String title) {
        GoldDetailFragment fragment = new GoldDetailFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected EmtyP initPresenter() {
        return new EmtyP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gold_detail;
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        String title = (String) bundle.get("title");
        mDateTv.setText(title);
    }
}
