package com.example.liapplication_demo.presenter;

import com.example.liapplication_demo.base.IBaseCallback;

public interface IFarmlandIdPresenter extends IBaseCallback {

    /**
     * 获取土地编号
     */
    void getFarmlandId();


    /**
     * 获取生产组编号
     */
    void getGroupId();
}
