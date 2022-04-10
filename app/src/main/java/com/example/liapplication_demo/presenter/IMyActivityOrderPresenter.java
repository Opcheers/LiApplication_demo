package com.example.liapplication_demo.presenter;

import com.example.liapplication_demo.base.IBasePresenter;
import com.example.liapplication_demo.view.IMyActivityOrderCallback;

public interface IMyActivityOrderPresenter extends IBasePresenter<IMyActivityOrderCallback> {

    /**
     * 拿到活动订单
     */
    void getActivityOrder();
}
