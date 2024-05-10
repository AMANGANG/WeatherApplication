package com.example.fragmentweather;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherRepository {
    private final WeatherApiService service;

    public WeatherRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(WeatherApiService.class);
    }

    public Call<WeatherForecastResponse> getWeatherForecast(String cityName, int days) {
        return service.getWeatherForecast(cityName, days, Constants.API_KEY);
    }
    public Call<WeatherData> getWeatherData(String cityName) {
        return service.getWeatherData(cityName, Constants.API_KEY, Constants.UNITS);
    }
}

