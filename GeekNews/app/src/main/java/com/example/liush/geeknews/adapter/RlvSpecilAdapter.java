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
import com.bumptech.glide.request.RequestOptions;
import com.example.liush.geeknews.R;
import com.example.liush.geeknews.bean.SpecailBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liush on 2019/4/18.
 */

public class RlvSpecilAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<SpecailBean.DataBean> mBeans;

    public RlvSpecilAdapter(Context context, ArrayList<SpecailBean.DataBean> beans) {
        mContext = context;
        mBeans = beans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_specilitem, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        RequestOptions options = new RequestOptions().centerCrop();
        Glide.with(mContext).load(mBeans.get(position).getThumbnail()).apply(options).into(holder1.mSpecilTp);
        holder1.mSpecilTv1.setText(mBeans.get(position).getDescription());
        holder1.mSpecilTv2.setText(mBeans.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return mBeans.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.specil_tp)
        ImageView mSpecilTp;
        @BindView(R.id.specil_tv1)
        TextView mSpecilTv1;
        @BindView(R.id.specil_tv2)
        TextView mSpecilTv2;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
