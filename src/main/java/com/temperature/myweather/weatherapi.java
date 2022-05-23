package com.temperature.myweather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface weatherapi {
    @GET("weather")
    Call<temperature> getweather(@Query("q") String cityname,
                                 @Query("appid") String apikey);
}
