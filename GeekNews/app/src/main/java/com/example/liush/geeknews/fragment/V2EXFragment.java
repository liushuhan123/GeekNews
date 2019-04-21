package com.example.liush.geeknews.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liush.geeknews.R;
import com.example.liush.geeknews.adapter.V2exVpAdapter;
import com.example.liush.geeknews.base.BaseFragment;
import com.example.liush.geeknews.bean.V2exTabBean;
import com.example.liush.geeknews.presenter.V2exPresenter;
import com.example.liush.geeknews.view.V2EXView;

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
public class V2EXFragment extends BaseFragment<V2EXView, V2exPresenter> implements V2EXView {
    private static final String TAG = "V2EXFragment";
    private String mUrl = "https://www.v2ex.com/";

    @BindView(R.id.v2_tab)
    TabLayout mV2Tab;
    @BindView(R.id.v2_vp)
    ViewPager mV2Vp;
    private ArrayList<String> mList;

    @Override
    protected V2exPresenter initPresenter() {
        return new V2exPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_v2_ex;
    }

    @Override
    protected void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final ArrayList<V2exTabBean> tabBeans = new ArrayList<>();
                    mList = new ArrayList<>();
                    Document document = Jsoup.connect(mUrl).get();
                    //查找id是Tabs的div元素,因为只有一个,直接调用了first()
                    Element first = document.select("div#Tabs").first();
                    //查找带有href属性的a元素
                    Elements all = first.select("a[href]");
                    for (Element element : all) {
                        String linkHref = element.attr("href");
                        String tab = element.text();
                        Log.d(TAG, "run: " + "linkHref" + linkHref);
                        tabBeans.add(new V2exTabBean(linkHref, tab));
                        mList.add(linkHref);
                    }

                    Log.d(TAG, "run: " + tabBeans.toString());

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ArrayList<Fragment> fragments = new ArrayList<>();
                            for (int i = 0; i < tabBeans.size(); i++) {
                                fragments.add(V2exBlankFragment.newInstance(mList.get(i)));
                            }
                            V2exVpAdapter adapter = new V2exVpAdapter(getChildFragmentManager(), fragments, tabBeans);
                            mV2Vp.setAdapter(adapter);
                            mV2Tab.setupWithViewPager(mV2Vp);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
