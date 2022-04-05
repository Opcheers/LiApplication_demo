package com.example.liapplication_demo.presenter;

import com.example.liapplication_demo.base.IBasePresenter;
import com.example.liapplication_demo.view.IActivityDetailCallback;

public interface IActivityDetailPresenter extends IBasePresenter<IActivityDetailCallback> {

    /**
     *  根据Id获取活动信息
     */
    void getActivityById(String actId);
}
