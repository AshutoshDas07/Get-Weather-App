package com.example.myweatherapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JSONPlaceHolderAPI {

    @GET("/data/2.5/weather")
    Call<Home_Fragment_Details> getPost(@Query("lat") double lat,
                                        @Query("lon") double lon,
                                        @Query("units") String units,
                                        @Query("appid") String key);
    @GET("/data/2.5/weather")
    Call<Home_Fragment_Details> getPost(@Query("q") String name,
                                        @Query("units") String units,
                                        @Query("appid") String key);
    @GET("/data/2.5/onecall")
    Call<Forecast_Fragment_Details> getFragPost(@Query("lat") String lat,
                                            @Query("lon") String lon,
                                            @Query("exclude") String exclude,
                                            @Query("units") String units,
                                            @Query("appid") String Key);
}
