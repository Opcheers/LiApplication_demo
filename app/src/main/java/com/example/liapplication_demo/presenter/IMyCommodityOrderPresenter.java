package com.example.liapplication_demo.presenter;

import com.example.liapplication_demo.base.IBasePresenter;
import com.example.liapplication_demo.view.IMyCommodityOrderCallback;

public interface IMyCommodityOrderPresenter extends IBasePresenter<IMyCommodityOrderCallback> {

    /**
     * 拿到活动订单
     */
    void getCommodityOrder();
}
