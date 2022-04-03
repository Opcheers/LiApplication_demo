package com.example.liapplication_demo.presenter;

import com.example.liapplication_demo.view.IHomeCallback;

public interface IHomePresenter {

    /**
     * 获取推荐活动
     */

    void getTopActivity();

    /**
     * 注册UI通知接口
     */
    void registerCallback(IHomeCallback callback);


    /**
     * 取消UI通知接口
     */
    void unregisterCallback(IHomeCallback callback);
}
