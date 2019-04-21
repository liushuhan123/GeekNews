package com.example.liush.geeknews.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liush.geeknews.R;
import com.example.liush.geeknews.adapter.V2exAdapter;
import com.example.liush.geeknews.adapter.V2exVpAdapter;
import com.example.liush.geeknews.base.BaseFragment;
import com.example.liush.geeknews.bean.V2exBean;
import com.example.liush.geeknews.presenter.EmtyP;
import com.example.liush.geeknews.view.EmtyV;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class V2exBlankFragment extends BaseFragment<EmtyV, EmtyP> implements EmtyV {

    private static final String TAG = "V2exBlankFragment";
    @BindView(R.id.rlv)
    RecyclerView mRlv;
    private String mType;
    String url = "https://www.v2ex.com";
    private ArrayList<V2exBean> mBeans;
    private ArrayList<V2exBean> mList;
    private V2exAdapter mAdapter;

    public static V2exBlankFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString("type", type);
        V2exBlankFragment fragment = new V2exBlankFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected EmtyP initPresenter() {
        return new EmtyP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_v2ex_blank;
    }

    @Override
    protected void initView() {

        mList = new ArrayList<>();
        mBeans = new ArrayList<>();
        mAdapter = new V2exAdapter(getActivity(), mList);
        mRlv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRlv.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        new Thread(new Runnable() {

            private String mTitle;
            private Elements mTitles;
            private String mText;
            private String mSrc;

            @Override
            public void run() {
                Bundle bundle = getArguments();
                mType = bundle.getString("type");
                Log.i(TAG, "initView: " + mType);
                try {
                    Document document = Jsoup.connect(url + mType).get();
                    if (document != null) {
                        Elements select = document.select("div.cell.item");
                        for (Element element : select) {
                            Elements imgs = element.select("table tr td img.avatar");
                            if (imgs.size() > 0) {
                                mSrc = imgs.get(0).attr("src");
                            }
                            Elements counts = element.select("table tr a.count_livid");
                            if (counts.size() > 0) {
                                mText = counts.get(0).text();
                            }
                            mTitles = element.select("table tr td span.item_title > a");
                            if (mTitles.size() > 0) {
                                mTitle = mTitles.get(0).text();
                                Log.d(TAG, "title: " + mTitles);
                            }
                            Elements all = element.select("table tr td span.topic_info");
                            if (all.size() > 0) {
                                String time = all.get(0).text();
                                final String[] split = time.split(" • ");
                                if (split.length > 3) {
                                    //程序员  •  a852695  •  5 分钟  •  前c最后回复来自 wun
                                    final V2exBean bean = new V2exBean(split[2] + " • " + split[3], split[1], mTitle, split[0], mText, mSrc);
                                    Log.d(TAG, "run: " + bean.toString());
                                    mBeans.add(bean);
                                }
                            }
                        }

                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mList.addAll(mBeans);
                            mAdapter.notifyDataSetChanged();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


}
