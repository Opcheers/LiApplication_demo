package com.example.liapplication_demo.presenter.impl;

import com.example.liapplication_demo.model.Api;
import com.example.liapplication_demo.model.domain.ActivityOrder;
import com.example.liapplication_demo.presenter.IMyActivityOrderPresenter;
import com.example.liapplication_demo.utils.LogUtils;
import com.example.liapplication_demo.utils.RetrofitManager;
import com.example.liapplication_demo.utils.UrlUtils;
import com.example.liapplication_demo.view.IMyActivityOrderCallback;

import java.net.HttpURLConnection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MyActivityOrderPresenterImpl implements IMyActivityOrderPresenter {

    IMyActivityOrderCallback mCallback = null;

    @Override
    public void registerCallback(IMyActivityOrderCallback callback) {
        mCallback = callback;
    }

    @Override
    public void unregisterCallback(IMyActivityOrderCallback callback) {
        mCallback = null;
    }

    @Override
    public void getActivityOrder() {
        //网络请求拿数据
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        String userId = "omb8z5KZxcC8lFs_kalaujLDwYxk";
        String getActivityOrderUrl = UrlUtils.createActivityOrderUrl(userId);
        Call<ActivityOrder> task = api.getActivityOrder(getActivityOrderUrl);
        task.enqueue(new Callback<ActivityOrder>() {
            @Override
            public void onResponse(Call<ActivityOrder> call, Response<ActivityOrder> response) {
                int code = response.code();
                if (code == HttpURLConnection.HTTP_OK) {
                    //请求成功
                    List<ActivityOrder.DataBean> activityOrders = response.body().getData();
                    LogUtils.d(MyActivityOrderPresenterImpl.this, "activityOrders --> " + activityOrders.toString());
                    mCallback.onActivityOrderLoaded(activityOrders);
                } else {
                    //请求失败
                    LogUtils.d(MyActivityOrderPresenterImpl.this, "请求失败...");
                }
            }

            @Override
            public void onFailure(Call<ActivityOrder> call, Throwable t) {
                //请求错误
                LogUtils.d(MyActivityOrderPresenterImpl.this, "请求错误..." + t);
            }
        });
    }
}
