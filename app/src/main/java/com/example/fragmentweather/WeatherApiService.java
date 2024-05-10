package com.example.fragmentweather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
/*
////TODO wrong naming
//// TODO please explain why is it public? what is the default access modifier of an interface?
Default Access modifier is private for interface .
WeatherApiService is public  so any class  can implement it.
*/
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
