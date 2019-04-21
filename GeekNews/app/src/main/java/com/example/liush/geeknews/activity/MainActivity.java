package com.example.liush.geeknews.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.liush.geeknews.R;
import com.example.liush.geeknews.base.BaseActivity;
import com.example.liush.geeknews.fragment.AboutFragment;
import com.example.liush.geeknews.fragment.CollectFragment;
import com.example.liush.geeknews.fragment.GankFragment;
import com.example.liush.geeknews.fragment.GoldFragment;
import com.example.liush.geeknews.fragment.SettingsFragment;
import com.example.liush.geeknews.fragment.V2EXFragment;
import com.example.liush.geeknews.fragment.WechatFragment;
import com.example.liush.geeknews.fragment.ZhiHuFragment;
import com.example.liush.geeknews.presenter.MainPresenter;
import com.example.liush.geeknews.view.MainView;
import com.example.liush.geeknews.view.SpecialView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<MainView, MainPresenter> implements MainView {
    @BindView(R.id.too)
    Toolbar mToo;
    @BindView(R.id.fragment_container)
    FrameLayout mFragmentContainer;
    @BindView(R.id.nav)
    NavigationView mNav;
    @BindView(R.id.dw)
    DrawerLayout mDw;
    @BindView(R.id.too_title)
    TextView mTooTitle;
    @BindView(R.id.search_view)
    MaterialSearchView mSearchView;
    @BindView(R.id.toolbar_container)
    FrameLayout mToolbarContainer;
    private ArrayList<String> mList;
    private ArrayList<Fragment> mFragments;
    private FragmentManager mFragmentManager;
    private final int ZHIHU = 0;
    private final int WECHAT = 1;
    private final int GANK = 2;
    private final int GOLD = 3;
    private final int V2EC = 4;
    private final int COLLECT = 5;
    private final int SETTINGS = 6;
    private final int ABOUT = 7;
    private int mLastFragmentPosition;
    private Fragment mHind;
    private SpecialView mSm;
    private MenuItem mSearchItem;

    //刘姝含

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initview() {
        //设置布局管理器
        mFragmentManager = getSupportFragmentManager();
        mToo.setTitle("");
        mTooTitle.setTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(mToo);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDw, mToo, R.string.app_name, R.string.app_name);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        mDw.addDrawerListener(toggle);
        toggle.syncState();
        //初始化fragment
        initFragment();
        initTitel();
        initViewFragment();

    }

    private void initViewFragment() {
        //第一次进入页面显示一个
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.add(R.id.fragment_container, mFragments.get(0));
        transaction.commit();
        mTooTitle.setText(mList.get(0));

    }

    private void initTitel() {
        mList = new ArrayList<>();
        mList.add("知乎日报");
        mList.add("微信精选");
        mList.add("干货集中营");
        mList.add("稀土挖金");
        mList.add("V2EX");
        mList.add("收藏");
        mList.add("设置");
        mList.add("关于");
    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        mFragments.add(new ZhiHuFragment());
        mFragments.add(new WechatFragment());
        mFragments.add(new GankFragment());
        mFragments.add(new GoldFragment());
        mFragments.add(new V2EXFragment());
        mFragments.add(new CollectFragment());
        mFragments.add(new SettingsFragment());
        mFragments.add(new AboutFragment());
    }

    @Override
    protected void initLstener() {
        //menu监听事件
        mNav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                //判断如果id没有这两个标题在点击
                if (id != R.id.info_title && id != R.id.options_title) {
                    item.setChecked(true);
                }
                switch (id) {
                    case R.id.zhihu:
                        switchFragment(ZHIHU);
                        mTooTitle.setText(mList.get(0));
                        break;
                    case R.id.wechat:
                        switchFragment(WECHAT);
                        mTooTitle.setText(mList.get(1));
                        break;
                    case R.id.gank:
                        switchFragment(GANK);
                        mTooTitle.setText(mList.get(2));
                        break;
                    case R.id.gold:
                        switchFragment(GOLD);
                        mTooTitle.setText(mList.get(3));
                        break;
                    case R.id.v2ex:
                        switchFragment(V2EC);
                        mTooTitle.setText(mList.get(4));
                        break;
                    case R.id.collect:
                        switchFragment(COLLECT);
                        mTooTitle.setText(mList.get(5));
                        break;
                    case R.id.settings:
                        switchFragment(SETTINGS);
                        mTooTitle.setText(mList.get(6));
                        break;
                    case R.id.about:
                        switchFragment(ABOUT);
                        mTooTitle.setText(mList.get(7));
                        break;
                }
                mDw.closeDrawer(Gravity.LEFT);
                return false;
            }
        });
        mSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //按下搜索或者提交的时候回调,
                //ToastUtil.showShort("提交的内容:"+query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //搜索框内容发生改变的回调,
                //ToastUtil.showShort(newText);
                return false;
            }
        });

        mSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //搜索框展示
                //ToastUtil.showShort("展示");
            }

            @Override
            public void onSearchViewClosed() {
                //搜索框隐藏
                //ToastUtil.showShort("关闭");
            }
        });
    }

    private void switchFragment(int zhihu) {
        Fragment fragment = mFragments.get(zhihu);
        //隐藏
        mHind = mFragments.get(mLastFragmentPosition);
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        //判断是否被添加过
        if (!fragment.isAdded()) {
            transaction.add(R.id.fragment_container, fragment);
        }
        transaction.hide(mHind);
        transaction.show(fragment);
        transaction.commit();
        //每一次运行一回就把你之前的页面下标存进去
        mLastFragmentPosition = zhihu;
        //显示或者隐藏搜索框
        if (zhihu == WECHAT || zhihu == GANK){
            mSearchItem.setVisible(true);
        }else {
            mSearchItem.setVisible(false);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutoo, menu);
        mSearchItem = menu.findItem(R.id.menu_search);
        //通过MenuItem得到SearchView
        mSm = (SpecialView) MenuItemCompat.getActionProvider(mSearchItem);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_search:
                break;
        }
        return super.onContextItemSelected(item);
    }
}
