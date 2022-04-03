package com.example.liapplication_demo.view;

import com.example.liapplication_demo.model.domain.FarmActivities;

import java.util.List;

public interface IHomeCallback  {

    void onTopActivityLoaded(List<FarmActivities.DataBean> farmActivities);
}
