package com.example.liapplication_demo.view;

import com.example.liapplication_demo.base.IBaseCallback;
import com.example.liapplication_demo.model.domain.Commodities;

import java.util.List;

public interface ICategoryPagerCallback extends IBaseCallback {

    /**
     * 商品数据加载回来
     * @param commodities
     */
    void onContentLoaded(List<Commodities.DataBean> commodities);


    /**
     * 拿到categoryId
     * @return
     */
    int getCategoryId();
}
