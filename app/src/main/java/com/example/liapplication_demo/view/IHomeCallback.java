package com.example.liapplication_demo.view;

import com.example.liapplication_demo.base.IBaseCallback;
import com.example.liapplication_demo.model.domain.FarmActivities;

import java.util.List;

public interface IHomeCallback  extends IBaseCallback {

    void onTopActivityLoaded(List<FarmActivities.DataBean> farmActivities);
}
