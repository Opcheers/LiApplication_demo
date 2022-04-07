package com.example.liapplication_demo.presenter.impl;

import com.example.liapplication_demo.model.Api;
import com.example.liapplication_demo.model.domain.FarmActivities;
import com.example.liapplication_demo.presenter.IHomePresenter;
import com.example.liapplication_demo.utils.LogUtils;
import com.example.liapplication_demo.utils.RetrofitManager;
import com.example.liapplication_demo.view.IHomeCallback;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomePresenterImpl implements IHomePresenter {
    private IHomeCallback mCallback = null;

    @Override
    public void getTopActivity() {

        if (mCallback != null) {
            mCallback.onLoading();
        }
        //加载推荐活动数据
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        Call<FarmActivities> task = api.getTopActivity();
        task.enqueue(new Callback<FarmActivities>() {
            @Override
            public void onResponse(Call<FarmActivities> call, Response<FarmActivities> response) {
                int code = response.code();
                LogUtils.d(HomePresenterImpl.this, "result code is --> " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    //请求成功
                    FarmActivities farmActivities = response.body();
                    LogUtils.d(HomePresenterImpl.this, farmActivities.toString());
                    if (mCallback!=null) {
                        mCallback.onTopActivityLoaded(farmActivities.getData());
                    }
                } else {
                    //请求失败
                    LogUtils.d(HomePresenterImpl.this, "请求失败...");
                    mCallback.onError();
                }
            }

            @Override
            public void onFailure(Call<FarmActivities> call, Throwable t) {
                LogUtils.d(HomePresenterImpl.this, "请求错误..."+t);
                mCallback.onError();
            }
        });
    }

    @Override
    public void registerCallback(IHomeCallback callback) {
        //通知ui
        this.mCallback = callback;
    }

    @Override
    public void unregisterCallback(IHomeCallback callback) {
        this.mCallback = null;

    }
}
