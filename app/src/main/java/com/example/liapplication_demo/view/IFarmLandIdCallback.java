package com.example.liapplication_demo.view;

import com.example.liapplication_demo.base.IBaseCallback;
import com.example.liapplication_demo.model.domain.Farmland;

import java.util.List;

public interface IFarmLandIdCallback extends IBaseCallback {

    /**
     * 通过UI加载生产组编号数据
     */
    void onGroupIdLoaded(List<String> groupId);

    /**
     * 通过UI加载土地编号
     * @param farmlands
     */
    void onFarmlandIdLoaded(List<Farmland> farmlands);
}
