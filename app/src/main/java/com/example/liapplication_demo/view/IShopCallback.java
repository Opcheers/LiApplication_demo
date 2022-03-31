package com.example.liapplication_demo.view;


import com.example.liapplication_demo.base.IBaseCallback;
import com.example.liapplication_demo.model.domain.Categories;

import java.util.List;

public interface IShopCallback extends IBaseCallback {
    void onCommodityCategoriesLoaded(List<Categories> categories);


}
