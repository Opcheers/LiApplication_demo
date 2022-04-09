package com.example.liapplication_demo.model;

import com.example.liapplication_demo.model.domain.Calligraphy;
import com.example.liapplication_demo.model.domain.Commodities;
import com.example.liapplication_demo.model.domain.FarmActivities;
import com.example.liapplication_demo.model.domain.FarmlandGroup;
import com.example.liapplication_demo.model.domain.FarmlandItem;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface Api {


    @GET("activity/findTop")
    Call<FarmActivities> getTopActivity();

    @GET("activity/findAll")
    Call<FarmActivities> getFarmActivities();

    @GET
    Call<Commodities> getCommoditiesByCategory(@Url String url);

    @GET
    Call<FarmActivities> getActivityDetailById(@Url String url);

    @GET("farmland/findAllGroup")
    Call<FarmlandGroup> getFarmlandGroup();

    @GET
    Call<FarmlandItem> getFarmlandItemByGroupId(@Url String url);

    @GET("calligraphy/findAll")
    Call<Calligraphy> getCalligraphy();

}
