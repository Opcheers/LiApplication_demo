package com.example.liapplication_demo.model;

import com.example.liapplication_demo.model.domain.Commodities;
import com.example.liapplication_demo.model.domain.FarmActivities;

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
}
