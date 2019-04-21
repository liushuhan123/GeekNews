package com.example.liush.geeknews.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.liush.geeknews.R;
import com.example.liush.geeknews.bean.GoldBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liush on 2019/4/19.
 */

public class RlvShowAdapter extends RecyclerView.Adapter{

    private Context mContext;
    private ArrayList<GoldBean> mBeans;

    public RlvShowAdapter(Context context, ArrayList<GoldBean> beans) {
        mContext = context;
        mBeans = beans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_show, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        final GoldBean bean = mBeans.get(position);
        holder1.mTv.setText(mBeans.get(position).title);
        holder1.mSc.setChecked(bean.isChecked);
        holder1.mSc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                bean.isChecked = isChecked;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBeans.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv)
        TextView mTv;
        @BindView(R.id.sc)
        SwitchCompat mSc;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
