package com.example.liapplication_demo.presenter.impl;

import com.example.liapplication_demo.model.Api;
import com.example.liapplication_demo.model.domain.Station;
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
    public void getCategories() {
        //加载分类数据
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        Call<Station> task = api.getStation();
        task.enqueue(new Callback<Station>() {
            @Override
            public void onResponse(Call<Station> call, Response<Station> response) {
                //数据结果
                int code = response.code();
                LogUtils.d(HomePresenterImpl.this, "result code is -->  "+code);
                if (code == HttpURLConnection.HTTP_OK){
                    //请求成功
                    Station station = response.body();
                    LogUtils.d(HomePresenterImpl.this, station.toString());
                    if (mCallback!=null) {
                        mCallback.onCategoriesLoaded(station);
                    }
                }else{
                    //请求失败
                    LogUtils.i(HomePresenterImpl.this, "请求失败...");
                }


            }

            @Override
            public void onFailure(Call<Station> call, Throwable t) {
                //加载失败的数据结果
                LogUtils.e(HomePresenterImpl.this, "请求错误..." + t);
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
