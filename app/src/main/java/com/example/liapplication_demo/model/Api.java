package com.example.liapplication_demo.model;

import com.example.liapplication_demo.model.domain.Commodities;
import com.example.liapplication_demo.model.domain.FarmActivities;
import com.example.liapplication_demo.model.domain.Station;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface Api {

    @GET("station/findAll")
    Call<Station> getStation();

    @GET("activity/findAll")
    Call<FarmActivities> getFarmActivities();

    @GET
    Call<Commodities> getCommoditiesByCategory(@Url String url);
}
