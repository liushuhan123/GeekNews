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
import com.example.liush.geeknews.bean.DailyBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liush on 2019/4/17.
 */

public class RlvDailyAdapter1 extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<DailyBean.StoriesBean> mNewsList;
    private ArrayList<DailyBean.TopStoriesBean> mBanners;
    private static final int TYPE_BANNER = 0;
    private static final int TYPE_TIME = 1;
    private static final int TYPE_NEWS = 2;
    private String mDate = "今日新闻";

    public RlvDailyAdapter1(Context context, ArrayList<DailyBean.StoriesBean> newsList, ArrayList<DailyBean.TopStoriesBean> banners) {
        mContext = context;
        mNewsList = newsList;
        mBanners = banners;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_BANNER) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.layout_dailyitem1, null);
            ViewHolder1 holder1 = new ViewHolder1(view);
            return holder1;
        } else if (viewType == TYPE_TIME) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.layout_dailyitem2, null);
            ViewHolder2 holder2 = new ViewHolder2(view);
            return holder2;
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.layout_dailyitem3, null);
            ViewHolder3 holder3 = new ViewHolder3(view);
            return holder3;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == TYPE_BANNER) {
            final ViewHolder1 holder1 = (ViewHolder1) holder;
            holder1.mBan.setImages(mBanners).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    DailyBean.TopStoriesBean path1 = (DailyBean.TopStoriesBean) path;
                    Glide.with(context).load(path1.getImage()).into(imageView);
                }
            }).start();
        } else if (viewType == TYPE_TIME) {
            ViewHolder2 holder1 = (ViewHolder2) holder;
            holder1.mDailyTv.setText(mDate);
        } else {
            int pos = position - 1;
            if (mBanners.size() > 0) {
                pos -= 1;
            }
            ViewHolder3 holder3 = (ViewHolder3) holder;
            holder3.mDailyTv1.setText(mNewsList.get(pos).getTitle());
            Glide.with(mContext).load(mNewsList.get(pos).getImages().get(0)).into(holder3.mDailyTp);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mBanners.size() > 0) {
            if (position == 0) {
                return TYPE_BANNER;
            } else if (position == 1) {
                return TYPE_TIME;
            } else {
                return TYPE_NEWS;
            }
        } else {
            if (position == 0) {
                return TYPE_TIME;
            } else {
                return TYPE_NEWS;
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mBanners.size() > 0) {
            return mNewsList.size() + 2;
        } else {
            return mNewsList.size() + 1;
        }
    }


    static class ViewHolder1 extends RecyclerView.ViewHolder {
        @BindView(R.id.ban)
        Banner mBan;

        ViewHolder1(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    class ViewHolder2 extends RecyclerView.ViewHolder {
        @BindView(R.id.daily_tv)
        TextView mDailyTv;

        ViewHolder2(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    class ViewHolder3 extends RecyclerView.ViewHolder {
        @BindView(R.id.daily_tp)
        ImageView mDailyTp;
        @BindView(R.id.daily_tv1)
        TextView mDailyTv1;

        ViewHolder3(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
