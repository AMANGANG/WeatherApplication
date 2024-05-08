package com.example.weatherapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Forecast extends Fragment {

    private RecyclerView recyclerView;
    private WeatherForecastAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forecast, container, false);

        recyclerView = view.findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        String cityName = getArguments().getString("cityName");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherApiService service = retrofit.create(WeatherApiService.class);
        Call<WeatherForecastResponse> call = service.getWeatherForecast(cityName, 7, "90b2bfcec116c18607aadc9b350e7b2d");

        call.enqueue(new Callback<WeatherForecastResponse>() {
            @Override
            public void onResponse(Call<WeatherForecastResponse> call, Response<WeatherForecastResponse> response) {
                if (response.isSuccessful()) {
                    adapter = new WeatherForecastAdapter(response.body().getList());
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(getActivity(), "Error: " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<WeatherForecastResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}
