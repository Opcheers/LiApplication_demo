package com.example.liapplication_demo.view;

import com.example.liapplication_demo.base.IBaseCallback;
import com.example.liapplication_demo.model.domain.FarmActivities;

import java.util.List;

public interface IActivityDetailCallback extends IBaseCallback {


    /**
     * 数据加载回来
     * @param farmActivity
     */
    void onActivityDetailLoded(List<FarmActivities.DataBean> farmActivity);
}
