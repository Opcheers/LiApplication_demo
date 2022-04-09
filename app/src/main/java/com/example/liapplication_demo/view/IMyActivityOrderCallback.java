package com.example.liapplication_demo.view;

import com.example.liapplication_demo.base.IBaseCallback;
import com.example.liapplication_demo.model.domain.ActivityOrder;

import java.util.List;

public interface IMyActivityOrderCallback extends IBaseCallback {
    /**
     * 加载活动订单
     */
    void onActivityOrderLoaded(List<ActivityOrder.DataBean> activityOrders);

}
