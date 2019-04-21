package com.example.liush.geeknews.presenter;

import com.example.liush.geeknews.base.BasePresenter;
import com.example.liush.geeknews.bean.DailyBean;
import com.example.liush.geeknews.model.DailyModel;
import com.example.liush.geeknews.net.CallBack;
import com.example.liush.geeknews.view.DailyView;

/**
 * Created by liush on 2019/4/17.
 */

public class DailyPresenter extends BasePresenter<DailyView> {


    private DailyModel mDailyModel;

    @Override
    protected void initModel() {
        mDailyModel = new DailyModel();
        mModels.add(mDailyModel);
    }

    public void getData() {
        mDailyModel.getData(new CallBack<DailyBean>() {
            @Override
            public void onSuccess(DailyBean bean) {
                if (bean != null) {
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
