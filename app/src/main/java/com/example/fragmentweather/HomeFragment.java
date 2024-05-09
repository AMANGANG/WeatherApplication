
package com.example.fragmentweather;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    private TextView temperatureTextView;
    private TextView maxTempTextView;
    private TextView minTempTextView;
    private TextView weatherDescriptionTextView;
    private TextView dayTextView;
    private TextView dateTextView;
    private TextView cityNameTextView;


    private View frame;

    private LottieAnimationView lottieAnimationView;

    private WeatherViewModel weatherViewModel;

    private String currentCityName = "jaipur";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        temperatureTextView = view.findViewById(R.id.temp);
        maxTempTextView = view.findViewById(R.id.maxtemp);
        minTempTextView = view.findViewById(R.id.mintemp);
        weatherDescriptionTextView = view.findViewById(R.id.typeofweather);
        dayTextView = view.findViewById(R.id.day);
        dateTextView = view.findViewById(R.id.date);
        cityNameTextView = view.findViewById(R.id.textView);
        lottieAnimationView = view.findViewById(R.id.lottieAnimationView);
        frame=view.findViewById(R.id.frame);
        weatherViewModel = new ViewModelProvider(this).get(WeatherViewModel.class);

    weatherViewModel.getSearchQuery().observe(getViewLifecycleOwner(),query->{
        if(query!=null){
            currentCityName=query;
        }
    });


        fetchWeatherData(currentCityName);




        return view;
    }

    private void fetchWeatherData(String cityName) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherApiservice service = retrofit.create(WeatherApiservice.class);
        Call<WeatherData> call = service.getWeatherData(cityName, "d38f4657f7041c386dd0e17c4eaebf1a", "metric");

        call.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                if (response.isSuccessful()) {
                    WeatherData weatherApp = response.body();
                    if (weatherApp != null) {
                        double temperature = weatherApp.getMain().getTemp();
                        double maxTemp = weatherApp.getMain().getTemp_max();
                        double minTemp = weatherApp.getMain().getTemp_min();
                        String condition = weatherApp.getWeather().get(0).getDescription();

                        temperatureTextView.setText(temperature + " °C");
                        maxTempTextView.setText("Max Temp: " + maxTemp + " °C");
                        minTempTextView.setText("Min Temp: " + minTemp + " °C");
                        weatherDescriptionTextView.setText(condition);
                        dayTextView.setText(dayName(System.currentTimeMillis()));
                        dateTextView.setText(date());
                        cityNameTextView.setText(cityName);
                        changeImagesAccordibdtocondition(condition);
                        Log.d("TAG", "Temperature: " + temperature + " °C");
                    } else {
                        Log.e("TAG", "Response body is null");
                    }
                } else {
                    Log.e("TAG", "Response unsuccessful. Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {
                Log.e("TAG", "Failed to fetch weather data: " + t.getMessage(), t);
            }
        });
    }

    private void changeImagesAccordibdtocondition(String conditions) {

        switch (conditions) {
            case "Clear Sky":
            case "Sunny":
            case "Clear":
                frame.setBackgroundResource(R.drawable.sunny_background);
                lottieAnimationView.setAnimation(R.raw.sun);
                break;
            case "few clouds":
            case "partly clouds":
            case "overcast clouds":
            case "Mist":
            case "broken clouds":
            case "Foggy":
            case "scattered clouds":
                frame.setBackgroundResource(R.drawable.colud_background);
                lottieAnimationView.setAnimation(R.raw.cloud);
                break;
            case "light rain":
            case "Drizzle":
            case "Moderate Rain":
            case "Showers":
            case "Heavy Rain":
            case "Rain":
            case "shower rain":
                frame.setBackgroundResource(R.drawable.rain_background);
                lottieAnimationView.setAnimation(R.raw.rain);
                break;
            case "Light Snow":
            case "Heavy Snow":
            case "Moderate Snow":
            case "Blizzard":
                frame.setBackgroundResource(R.drawable.snow_background);
                lottieAnimationView.setAnimation(R.raw.snow);
                break;
            default:
                frame.setBackgroundResource(R.drawable.sunny_background);
                lottieAnimationView.setAnimation(R.raw.sun);
                break;
        }
    }

    private String date() {
        SimpleDateFormat date = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
        return date.format(new Date());
    }

    private String dayName(long timestamp) {
        SimpleDateFormat day = new SimpleDateFormat("EEEE", Locale.getDefault());
        return day.format(new Date(timestamp));
    }
}
