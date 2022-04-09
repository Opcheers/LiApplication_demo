package com.example.liapplication_demo.presenter.impl;

import com.example.liapplication_demo.model.Api;
import com.example.liapplication_demo.model.domain.CommodityOrder;
import com.example.liapplication_demo.presenter.IMyCommodityOrderPresenter;
import com.example.liapplication_demo.utils.LogUtils;
import com.example.liapplication_demo.utils.RetrofitManager;
import com.example.liapplication_demo.utils.UrlUtils;
import com.example.liapplication_demo.view.IMyCommodityOrderCallback;

import java.net.HttpURLConnection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MyCommodityOrderPresenterImpl implements IMyCommodityOrderPresenter {

    IMyCommodityOrderCallback mCallback = null;

    @Override
    public void registerCallback(IMyCommodityOrderCallback callback) {
        mCallback = callback;
    }

    @Override
    public void unregisterCallback(IMyCommodityOrderCallback callback) {
        mCallback = null;
    }

    @Override
    public void getCommodityOrder() {
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        String userId = "omb8z5KZxcC8lFs_kalaujLDwYxk";
        String url = UrlUtils.createCommodityOrderUrl(userId);
        Call<CommodityOrder> task = api.getCommodityOrder(url);
        task.enqueue(new Callback<CommodityOrder>() {
            @Override
            public void onResponse(Call<CommodityOrder> call, Response<CommodityOrder> response) {
                int code = response.code();
                if (code == HttpURLConnection.HTTP_OK) {
                    List<CommodityOrder.DataBean> dataBeans = response.body().getData();
                    LogUtils.d(MyCommodityOrderPresenterImpl.this, "commodity order --> " + dataBeans.toString());
                    mCallback.onCommodityOrderLoaded(dataBeans);
                } else {
                    LogUtils.d(MyCommodityOrderPresenterImpl.this, "请求失败...");
                }
            }

            @Override
            public void onFailure(Call<CommodityOrder> call, Throwable t) {
                LogUtils.d(MyCommodityOrderPresenterImpl.this, "请求错误..." + t);
            }

        });
    }
}
