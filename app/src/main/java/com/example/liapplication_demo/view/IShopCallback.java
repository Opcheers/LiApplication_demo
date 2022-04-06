package com.example.liapplication_demo.view;


import com.example.liapplication_demo.base.IBaseCallback;
import com.example.liapplication_demo.model.domain.Categories;

import java.util.List;

public interface IShopCallback extends IBaseCallback {

    /**
     * 通知ui加载数据
     * @param categories
     */
    void onCommodityCategoriesLoaded(List<Categories> categories);


}
