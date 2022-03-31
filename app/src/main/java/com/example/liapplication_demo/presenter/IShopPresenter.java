package com.example.liapplication_demo.presenter;

import com.example.liapplication_demo.base.IBasePresenter;
import com.example.liapplication_demo.view.IShopCallback;

public interface IShopPresenter extends IBasePresenter<IShopCallback> {


    /**
     * 获取商品
     */
    void getCommodityCategories();

}
