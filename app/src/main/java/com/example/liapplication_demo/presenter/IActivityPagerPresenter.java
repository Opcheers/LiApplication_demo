package com.example.liapplication_demo.presenter;

import com.example.liapplication_demo.base.IBasePresenter;
import com.example.liapplication_demo.view.IFarmActivitiesCallback;

/**
 * 活动界面
 */
public interface IActivityPagerPresenter extends IBasePresenter<IFarmActivitiesCallback> {

    /**
     * 获取活动列表
     */
    void getFarmActivities();

}
