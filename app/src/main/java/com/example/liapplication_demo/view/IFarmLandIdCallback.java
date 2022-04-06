package com.example.liapplication_demo.view;

import com.example.liapplication_demo.base.IBaseCallback;

import java.util.List;

public interface IFarmLandIdCallback extends IBaseCallback {

    /**
     * 通过UI加载生产组编号数据
     */
    void onGroupIdLoaded(List<Integer> groupId);

    /**
     * 通过UI加载土地编号
     * @param farmlandId
     */
    void onFarmlandIdLoaded(List<Integer> farmlandId);
}
