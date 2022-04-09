package com.example.liapplication_demo.presenter;

import com.example.liapplication_demo.base.IBasePresenter;
import com.example.liapplication_demo.view.ICalligraphyCallback;

public interface ICalligraphyPresenter extends IBasePresenter<ICalligraphyCallback> {

    /**
     * 网络请求字画数据
     */
    void getCalligraphy();
}
