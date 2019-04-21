package com.example.liush.geeknews.presenter;

import com.example.liush.geeknews.base.BasePresenter;
import com.example.liush.geeknews.bean.SpecailBean;
import com.example.liush.geeknews.model.SpecialMoedl;
import com.example.liush.geeknews.net.CallBack;
import com.example.liush.geeknews.view.SpecialView;

/**
 * Created by liush on 2019/4/3.
 */

public class SpecialPresnetr extends BasePresenter<SpecialView> {

    private SpecialMoedl mMoedl;

    @Override
    protected void initModel() {
        mMoedl = new SpecialMoedl();
        mModels.add(mMoedl);
    }

    public void getData() {
        mMoedl.getData(new CallBack<SpecailBean>() {
            @Override
            public void onSuccess(SpecailBean bean) {
                if (bean!=null){
                    if (mView!=null){
                        mView.setData(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}
