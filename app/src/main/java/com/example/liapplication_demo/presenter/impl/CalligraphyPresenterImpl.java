package com.example.liapplication_demo.presenter.impl;

import com.example.liapplication_demo.model.Api;
import com.example.liapplication_demo.model.domain.Calligraphy;
import com.example.liapplication_demo.presenter.ICalligraphyPresenter;
import com.example.liapplication_demo.utils.LogUtils;
import com.example.liapplication_demo.utils.RetrofitManager;
import com.example.liapplication_demo.view.ICalligraphyCallback;

import java.net.HttpURLConnection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CalligraphyPresenterImpl implements ICalligraphyPresenter {


   ICalligraphyCallback mCallback = null;
   @Override
   public void registerCallback(ICalligraphyCallback callback) {
      mCallback = callback;
   }

   @Override
   public void unregisterCallback(ICalligraphyCallback callback) {
      mCallback = null;
   }

   @Override
   public void getCalligraphy() {
      if (mCallback != null) {
         mCallback.onLoading();
      }
      Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
      Api api = retrofit.create(Api.class);
      Call<Calligraphy> task = api.getCalligraphy();
      task.enqueue(new Callback<Calligraphy>() {
         @Override
         public void onResponse(Call<Calligraphy> call, Response<Calligraphy> response) {
            int code = response.code();
            if (code == HttpURLConnection.HTTP_OK) {
               //请求成功
               List<Calligraphy.DataBean> calligraphy = response.body().getData();
               LogUtils.d(CalligraphyPresenterImpl.this, " calligraphy --> " + calligraphy.toString());
               mCallback.onCalligraphyLoaded(calligraphy);
            }else{
               //请求失败
               LogUtils.d(CalligraphyPresenterImpl.this, "请求失败...");

            }
         }

         @Override
         public void onFailure(Call<Calligraphy> call, Throwable t) {
            LogUtils.d(CalligraphyPresenterImpl.this, "请求错误..." + t);

         }
      });
   }
}
