package com.example.liush.geeknews.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.liush.geeknews.R;
import com.example.liush.geeknews.bean.HotBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liush on 2019/4/18.
 */

public class RlvHotAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<HotBean.RecentBean> mBeans;

    public RlvHotAdapter(Context context, ArrayList<HotBean.RecentBean> beans) {
        mContext = context;
        mBeans = beans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_hotitem, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        Glide.with(mContext).load(mBeans.get(position).getThumbnail()).into(holder1.mHotTp);
        holder1.mHotTv.setText(mBeans.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mBeans.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.hot_tp)
        ImageView mHotTp;
        @BindView(R.id.hot_tv)
        TextView mHotTv;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
