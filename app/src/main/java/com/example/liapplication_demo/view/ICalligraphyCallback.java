package com.example.liapplication_demo.view;

import com.example.liapplication_demo.base.IBaseCallback;
import com.example.liapplication_demo.model.domain.Calligraphy;

import java.util.List;

public interface ICalligraphyCallback extends IBaseCallback {

    /**
     * 加载字画数据
     * @param calligraphyList
     */
    void onCalligraphyLoaded(List<Calligraphy.DataBean> calligraphyList);
}
