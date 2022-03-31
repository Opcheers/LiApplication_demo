package com.example.liapplication_demo.presenter;

import com.example.liapplication_demo.base.IBasePresenter;
import com.example.liapplication_demo.view.ICategoryPagerCallback;

public interface ICategoryPagerPresenter extends IBasePresenter<ICategoryPagerCallback> {


    /**
     * 通过category查找对应商品类别
     * @param categoryTitle
     * @param materialId
     */
    void getContentByCategory(String categoryTitle, int materialId);


    /**
     * 下拉加载更多
     * @param categoryId
     */
    void loadMore(int categoryId);


    /**
     * 网络不行重新加载
     * @param categoryId
     */
    void reload(int categoryId);

}
