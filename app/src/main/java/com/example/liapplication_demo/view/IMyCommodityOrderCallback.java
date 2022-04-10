package com.example.liapplication_demo.view;

import com.example.liapplication_demo.base.IBaseCallback;
import com.example.liapplication_demo.model.domain.CommodityOrder;

import java.util.List;

public interface IMyCommodityOrderCallback extends IBaseCallback {

    /**
     * 加载商品订单
     */
    void onCommodityOrderLoaded(List<CommodityOrder.DataBean> commodityOrders);
}
