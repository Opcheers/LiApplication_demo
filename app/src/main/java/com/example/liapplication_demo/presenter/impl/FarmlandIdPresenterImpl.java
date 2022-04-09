package com.example.liapplication_demo.presenter.impl;

import com.example.liapplication_demo.model.Api;
import com.example.liapplication_demo.model.domain.Farmland;
import com.example.liapplication_demo.model.domain.FarmlandGroup;
import com.example.liapplication_demo.model.domain.FarmlandItem;
import com.example.liapplication_demo.presenter.IFarmlandIdPresenter;
import com.example.liapplication_demo.utils.LogUtils;
import com.example.liapplication_demo.utils.RetrofitManager;
import com.example.liapplication_demo.utils.UrlUtils;
import com.example.liapplication_demo.view.IFarmLandIdCallback;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FarmlandIdPresenterImpl implements IFarmlandIdPresenter {

    IFarmLandIdCallback mCallback = null;

    @Override
    public void getFarmlandId(String groupId) {
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        String farmlandIdUrl = UrlUtils.createFarmlandIdUrl(groupId);
        Call<FarmlandItem> task = api.getFarmlandItemByGroupId(farmlandIdUrl);
        task.enqueue(new Callback<FarmlandItem>() {
            @Override
            public void onResponse(Call<FarmlandItem> call, Response<FarmlandItem> response) {
                int code = response.code();
                if (code == HttpURLConnection.HTTP_OK) {
                    //请求成功

                    FarmlandItem farmlandItem = response.body();
                    List<Farmland> farmlands = farmlandItem.getData();
                    //通知UI
                    mCallback.onFarmlandIdLoaded(farmlands);
                }else{
                    //请求失败
                    LogUtils.d(FarmlandIdPresenterImpl.this, "请求失败...");
                }
            }

            @Override
            public void onFailure(Call<FarmlandItem> call, Throwable t) {
                //请求错误
                LogUtils.d(FarmlandIdPresenterImpl.this, "请求错误..." + t);

            }
        });

    }

    @Override
    public void getGroupId() {

        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        Call<FarmlandGroup> task = api.getFarmlandGroup();
        task.enqueue(new Callback<FarmlandGroup>() {
            @Override
            public void onResponse(Call<FarmlandGroup> call, Response<FarmlandGroup> response) {
                int code = response.code();
                if (code == HttpURLConnection.HTTP_OK) {
                    //请求成功
                    FarmlandGroup farmlandGroup = response.body();
                    //拿到groupId
                    List<String> groupIdList = new ArrayList<>();
                    List<FarmlandGroup.DataBean> groupBean = farmlandGroup.getData();
                    for (FarmlandGroup.DataBean item : groupBean) {
                        groupIdList.add(item.getGroup());
                    }
                    //通知UI
                    mCallback.onGroupIdLoaded(groupIdList);
                } else {
                    //请求失败
                    LogUtils.d(FarmlandIdPresenterImpl.this, "请求失败...");
                }
            }

            @Override
            public void onFailure(Call<FarmlandGroup> call, Throwable t) {
                //请求错误
                LogUtils.d(FarmlandIdPresenterImpl.this, "请求错误..." + t);
            }
        });
    }
    public void registerCallback(IFarmLandIdCallback callback) {
        mCallback = callback;
    }

    @Override
    public void unregisterCallback(IFarmLandIdCallback callback) {
        this.mCallback = null;
    }
}
