package com.example.liapplication_demo.model;

import com.example.liapplication_demo.model.domain.ActivityOrder;
import com.example.liapplication_demo.model.domain.Calligraphy;
import com.example.liapplication_demo.model.domain.Commodities;
import com.example.liapplication_demo.model.domain.CommodityOrder;
import com.example.liapplication_demo.model.domain.FarmActivities;
import com.example.liapplication_demo.model.domain.FarmlandGroup;
import com.example.liapplication_demo.model.domain.FarmlandItem;
import com.example.liapplication_demo.model.domain.PostActivityOrder;
import com.example.liapplication_demo.model.domain.PostResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface Api {


    @GET("activity/findTop")
    Call<FarmActivities> getTopActivity();

    @GET("activity/findAll")
    Call<FarmActivities> getFarmActivities();

    @GET
    Call<Commodities> getCommoditiesByCategory(@Url String url);

    @GET
    Call<FarmlandItem> getFarmlandItemByGroupId(@Url String url);

    @GET("calligraphy/findAll")
    Call<Calligraphy> getCalligraphy();

    @GET("farmland/findAllGroup")
    Call<FarmlandGroup> getFarmlandGroup();

    @GET
    Call<ActivityOrder> getActivityOrder(@Url String url);

    @GET
    Call<CommodityOrder> getCommodityOrder(@Url String url);

    @POST
    Call<PostResult> sendVerifyCodeSms(@Url String url);

    @POST
    Call<PostResult> login(@Url String url);

    @POST("actOrder/create/")
    Call<PostResult> createActOrder(@Body PostActivityOrder activityOrder);
}
