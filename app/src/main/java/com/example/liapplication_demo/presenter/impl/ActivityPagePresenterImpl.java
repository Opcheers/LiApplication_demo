package com.example.liapplication_demo.presenter.impl;

import com.example.liapplication_demo.model.Api;
import com.example.liapplication_demo.model.domain.FarmActivities;
import com.example.liapplication_demo.presenter.IActivityPagerPresenter;
import com.example.liapplication_demo.utils.LogUtils;
import com.example.liapplication_demo.utils.RetrofitManager;
import com.example.liapplication_demo.view.IFarmActivitiesCallback;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ActivityPagePresenterImpl implements IActivityPagerPresenter {

    private IFarmActivitiesCallback mCallback = null;

    /**
     * 加载活动数据
     */
    @Override
    public void getFarmActivities() {
        if (mCallback != null) {
            mCallback.onLoading();
        }
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        Call<FarmActivities> task = api.getFarmActivities();
        task.enqueue(new Callback<FarmActivities>() {
            @Override
            public void onResponse(Call<FarmActivities> call, Response<FarmActivities> response) {
                //数据结果
                int code = response.code();
                LogUtils.d(ActivityPagePresenterImpl.this, "result code --> "+ code);
                if (code == HttpURLConnection.HTTP_OK) {
                    //请求成功
                    FarmActivities farmActivities = response.body();
                    LogUtils.d(ActivityPagePresenterImpl.this, farmActivities.toString());
                    //通知UI更新
                    if (mCallback != null) {
                        if (farmActivities == null ||farmActivities.getData().size() == 0) {
                            mCallback.onEmpty();
                        }else {
                            mCallback.onFarmActivitiesLoaded(farmActivities.getData());
                        }
                    }
                }else{
                    //请求失败
                    LogUtils.i(ActivityPagePresenterImpl.this, "请求失败......");
                    if (mCallback != null) {
                        mCallback.onNetworkError();
                    }
                }
            }

            @Override
            public void onFailure(Call<FarmActivities> call, Throwable t) {
                //加载失败的结果
                LogUtils.e(ActivityPagePresenterImpl.this, "请求错误......"+t);
                if (mCallback != null) {
                    mCallback.onNetworkError();
                }
            }
        });
    }

    @Override
    public void registerCallback(IFarmActivitiesCallback callback) {
        this.mCallback = callback;
    }

    @Override
    public void unregisterCallback(IFarmActivitiesCallback callback) {
        this.mCallback = null;
    }
}
