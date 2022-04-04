package com.example.liapplication_demo.presenter.impl;

import com.example.liapplication_demo.model.Api;
import com.example.liapplication_demo.model.domain.FarmActivities;
import com.example.liapplication_demo.presenter.IActivityDetailPresenter;
import com.example.liapplication_demo.utils.LogUtils;
import com.example.liapplication_demo.utils.RetrofitManager;
import com.example.liapplication_demo.utils.UrlUtils;
import com.example.liapplication_demo.view.IActivityDetailCallback;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ActivityDetailPresenterImpl implements IActivityDetailPresenter {


    private IActivityDetailCallback mCallback = null;
    @Override
    public void getActivityById(String actId) {
        if (mCallback != null) {
            mCallback.onLoading();
        }
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        String activityDetailUrl = UrlUtils.createActivityDetailUrl(actId);

        LogUtils.d(ActivityDetailPresenterImpl.this, "activity detail url --> " + activityDetailUrl);
        Call<FarmActivities> task = api.getActivityDetailById(activityDetailUrl);
        task.enqueue(new Callback<FarmActivities>() {
            @Override
            public void onResponse(Call<FarmActivities> call, Response<FarmActivities> response) {
                int code = response.code();
                LogUtils.d(ActivityDetailPresenterImpl.this, "code --> " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    FarmActivities activities = response.body();
                    LogUtils.d(ActivityDetailPresenterImpl.this, "activity --> " + activities.toString());
                }
            }

            @Override
            public void onFailure(Call<FarmActivities> call, Throwable t) {

            }
        });
    }

    @Override
    public void registerCallback(IActivityDetailCallback callback) {

    }

    @Override
    public void unregisterCallback(IActivityDetailCallback callback) {

    }
}
