package com.example.liapplication_demo.view;

import com.example.liapplication_demo.model.domain.Commodities;

import java.util.List;

public interface ICategoryPagerCallback {

    /**
     * 数据加载回来
     * @param commodities
     */
    void onContentLoaded(List<Commodities.DataBean> commodities);

    /**
     * 网络错误
     * @param categoryId
     */
    void onNetworkError(int categoryId);

    /**
     * 加载中
     * @param categoryId
     */
    void onLoading(int categoryId);

    /**
     * 数据为空
     * @param categoryId
     */
    void onEmpty(int categoryId);

    void onLoadMoreError(int categoryId);

    void onLoadMoreEmpty(int categotyId);

    void onLoadMoreLoaded(List<Commodities.DataBean> commodities, int categoryId);
}
