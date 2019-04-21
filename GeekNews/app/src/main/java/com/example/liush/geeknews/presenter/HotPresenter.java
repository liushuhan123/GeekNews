package com.example.liush.geeknews.presenter;

import com.example.liush.geeknews.base.BasePresenter;
import com.example.liush.geeknews.bean.HotBean;
import com.example.liush.geeknews.model.HotModel;
import com.example.liush.geeknews.net.CallBack;
import com.example.liush.geeknews.view.HotView;

/**
 * Created by liush on 2019/4/18.
 */

public class HotPresenter extends BasePresenter<HotView> {

    private HotModel mMode;

    @Override
    protected void initModel() {
        mMode = new HotModel();
        mModels.add(mMode);
    }

    public void getData() {
        mMode.getData(new CallBack<HotBean>() {
            @Override
            public void onSuccess(HotBean bean) {
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
