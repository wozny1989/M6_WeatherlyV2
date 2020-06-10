package com.pksroczynski.weatherly.api;

import com.pksroczynski.weatherly.models.Weatherly;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("/data/2.5/weather")
    Single<Response<Weatherly>> getWeather(@Query("q") String name, @Query("appid") String appid);
}
