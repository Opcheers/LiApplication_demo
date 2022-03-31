package com.example.liapplication_demo.presenter.impl;

import com.example.liapplication_demo.model.domain.Categories;
import com.example.liapplication_demo.presenter.IShopPresenter;
import com.example.liapplication_demo.utils.Constants;
import com.example.liapplication_demo.view.IShopCallback;

import java.util.ArrayList;
import java.util.List;

public class ShopPresenterImpl implements IShopPresenter {

    private IShopCallback mCallback = null;
    @Override
    public void getCommodityCategories() {
        if (mCallback != null) {
            mCallback.onLoading();
        }
        //创建tab类别数据
        List<Categories> categories = new ArrayList<>();
        for (int i = 0; i < Constants.COMMODITY_CATEGORY_TABS.length; i++) {
            Categories item = new Categories();
            item.id = i;
            item.title = Constants.COMMODITY_CATEGORY_TABS[i];
            categories.add(item);
        }

        if (mCallback != null) {
            if (categories == null || categories.size() == 0) {
                mCallback.onEmpty();
            } else {
                mCallback.onCommodityCategoriesLoaded(categories);
            }
        }
    }

    @Override
    public void registerCallback(IShopCallback callback) {
        this.mCallback = callback;
    }

    @Override
    public void unregisterCallback(IShopCallback callback) {
        this.mCallback = null;

    }

}
