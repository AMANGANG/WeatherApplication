package com.example.weatherapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApiService {
    @GET("forecast")
    Call<WeatherForecastResponse> getWeatherForecast(
            @Query("q") String cityName,
            @Query("cnt") int count,
            @Query("appid") String apiKey
    );

    @GET("weather")
    Call<WeatherData>getWeatherData(
            @Query("q") String city,
            @Query("appid") String apiid,
            @Query("units") String units
    );



}

