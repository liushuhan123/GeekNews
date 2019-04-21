package com.example.liush.geeknews.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.liush.geeknews.R;
import com.example.liush.geeknews.bean.V2exBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2019/3/21 0021.
 */

public class V2exAdapter extends RecyclerView.Adapter {
    private static final String TAG = "V2exAdapter";
    private final Context mContext;
    public ArrayList<V2exBean> mList;

    public V2exAdapter(Context context, ArrayList<V2exBean> list) {

        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.v2ex_item, null, false);
        return new ViewHodler(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHodler holder1 = (ViewHodler) holder;
        V2exBean bean = mList.get(position);
        Log.d(TAG, "onBindViewHolder: "+bean.getAuthor());
        holder1.mAuthor.setText(bean.getAuthor());
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.ic_launcher);
        Log.d(TAG, "onBindViewHolder: "+bean.getImg());
        Glide.with(mContext).load("https:"+bean.getImg()).apply(options).into(holder1.img);
        holder1.mCount.setText(bean.getCount());
        holder1.mSecondTab.setText(bean.getSecondtab());
        holder1.mTime.setText(bean.getTime());
        holder1.mTitle.setText(bean.getTitle());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    class ViewHodler extends RecyclerView.ViewHolder {

        private final TextView mAuthor;
        private final ImageView img;
        private final TextView mTime;
        private final TextView mTitle;
        private final TextView mSecondTab;
        private final TextView mCount;

        public ViewHodler(View itemView) {
            super(itemView);
            mAuthor = itemView.findViewById(R.id.author);
            img = itemView.findViewById(R.id.img);
            mTime = itemView.findViewById(R.id.time);
            mTitle = itemView.findViewById(R.id.title);
            mSecondTab = itemView.findViewById(R.id.secondTab);
            mCount = itemView.findViewById(R.id.count);
        }
    }
}
