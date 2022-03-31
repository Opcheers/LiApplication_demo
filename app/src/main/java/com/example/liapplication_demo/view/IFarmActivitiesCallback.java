package com.example.liapplication_demo.view;

import com.example.liapplication_demo.base.IBaseCallback;
import com.example.liapplication_demo.model.domain.FarmActivities;

import java.util.List;

public interface IFarmActivitiesCallback extends IBaseCallback {

    void onFarmActivitiesLoaded(List<FarmActivities.DataBean> farmActivities);

}
