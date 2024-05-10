package com.example.fragmentweather;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

////TODO please indent your code
public class ForecastFragment extends Fragment {


    private RecyclerView recyclerView;
    private WeatherForecastAdapter adapter;
    private String cityName = "jaipur";

    private WeatherViewModel weatherViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forecast, container, false);

        recyclerView = view.findViewById(R.id.recyclerview1);
        ////TODO please explain what is LinearLayoutManger
        //So its basically use to layout  items in the list in a linear way one after the other.
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        weatherViewModel = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);


        weatherViewModel.getSearchQuery().observe(getViewLifecycleOwner(), query -> {
            if (query != null) {
                cityName = query;
                loadWeatherData(cityName);
            }
        });


        return view;
    }

    ////TODO this code should be part of repository, repository should be only accessible to viewmodel and not the view, this can lead to ANR
    private void loadWeatherData(String cityName) {
        ////TODO URL is global should be part of Constants file
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherApiService service = retrofit.create(WeatherApiService.class);
        ////TODO what is the need of 2 different APIKeys different keys in HomeFragment and ForecastFragment
        Call<WeatherForecastResponse> call = service.getWeatherForecast(cityName, 8, "90b2bfcec116c18607aadc9b350e7b2d");

        call.enqueue(new Callback<WeatherForecastResponse>() {
            @Override
            public void onResponse(Call<WeatherForecastResponse> call, Response<WeatherForecastResponse> response) {
                if (response.isSuccessful()) {
                    //// TODO wrong place to create the adapter, could have just added the list to the adapter
                    adapter = new WeatherForecastAdapter(response.body().getList());
                    //// TODO wrong place to set the adapter
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
    }
}