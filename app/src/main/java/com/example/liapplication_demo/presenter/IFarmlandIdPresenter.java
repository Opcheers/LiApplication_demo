package com.example.liapplication_demo.presenter;

import com.example.liapplication_demo.base.IBasePresenter;
import com.example.liapplication_demo.view.IFarmLandIdCallback;

public interface IFarmlandIdPresenter extends IBasePresenter<IFarmLandIdCallback> {

    /**
     * 获取土地编号
     * @param groupId
     */
    void getFarmlandId(String groupId);


    /**
     * 获取生产组编号
     */
    void getGroupId();
}
