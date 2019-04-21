package com.example.liush.geeknews.presenter;

import com.example.liush.geeknews.base.BasePresenter;
import com.example.liush.geeknews.bean.CalendarBean;
import com.example.liush.geeknews.model.CalendarModel;
import com.example.liush.geeknews.net.CallBack;
import com.example.liush.geeknews.view.CalendarView;

/**
 * Created by liush on 2019/4/18.
 */

public class CalendarPresenter extends BasePresenter<CalendarView> implements CallBack<CalendarBean> {

    private CalendarModel mModel;

    @Override
    protected void initModel() {
        mModel = new CalendarModel();
        mModels.add(mModel);
    }

    public void getData(int page) {
        mModel.getData(this, page);
    }

    @Override
    public void onSuccess(CalendarBean bean) {
        mView.setData(bean);
    }

    @Override
    public void onFail(String msg) {

    }
}
