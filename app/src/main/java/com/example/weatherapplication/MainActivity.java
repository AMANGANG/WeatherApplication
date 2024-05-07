package com.example.weatherapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView temperatureTextView;
    private TextView maxTempTextView;
    private TextView minTempTextView;
    private TextView weatherDescriptionTextView;
    private TextView dayTextView;
    private TextView dateTextView;
    private TextView cityNameTextView;
    private Button goToForecastButton;
    private SearchView searchView;

    private LottieAnimationView lottieAnimationView;

    private String currentCityName = "jaipur"; // Default city name

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        temperatureTextView = findViewById(R.id.temp);
        maxTempTextView = findViewById(R.id.maxtemp);
        minTempTextView = findViewById(R.id.mintemp);
        weatherDescriptionTextView = findViewById(R.id.typeofweather);
        dayTextView = findViewById(R.id.day);
        dateTextView = findViewById(R.id.date);
        cityNameTextView = findViewById(R.id.textView);
        goToForecastButton = findViewById(R.id.button);
        searchView = findViewById(R.id.searchView);
        lottieAnimationView=findViewById(R.id.lottieAnimationView);

        goToForecastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("cityName", currentCityName);
                startActivity(intent);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                currentCityName = query;
                fetchWeatherData(currentCityName);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        fetchWeatherData(currentCityName);
    }

    private void fetchWeatherData(String cityName) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherApiService service = retrofit.create(WeatherApiService.class);
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
                        String condition = weatherApp.getWeather().get(0).getMain();

                        temperatureTextView.setText(String.format(Locale.getDefault(), "%.2f °C", temperature));
                        maxTempTextView.setText(String.format(Locale.getDefault(), "Max Temp: %.2f °C", maxTemp));
                        minTempTextView.setText(String.format(Locale.getDefault(), "Min Temp: %.2f °C", minTemp));
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
                findViewById(R.id.main).setBackgroundResource(R.drawable.sunny_background);
                lottieAnimationView.setAnimation(R.raw.sun);
                break;
            case "Clouds":
            case "PartlyClouds":
            case "Overcast":
            case "Mist":
            case "Foggy":
                findViewById(R.id.main).setBackgroundResource(R.drawable.colud_background);
                lottieAnimationView.setAnimation(R.raw.cloud);
                break;
            case "Light Rain":
            case "Drizzle":
            case "Moderate Rain":
            case "Showers":
            case "Heavy Rain":
            case "Rain":
                findViewById(R.id.main).setBackgroundResource(R.drawable.rain_background);
                lottieAnimationView.setAnimation(R.raw.rain);
                break;
            case "Light Snow":
            case "Heavy Snow":
            case "Moderate Snow":
            case "Blizzard":
                findViewById(R.id.main).setBackgroundResource(R.drawable.snow_background);
                lottieAnimationView.setAnimation(R.raw.snow);

                break;
            default:
                findViewById(R.id.main).setBackgroundResource(R.drawable.sunny_background);
                lottieAnimationView.setAnimation(R.raw.sun);

                break;

        }
    }

    private String date() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
        return sdf.format(new Date());
    }

    private String dayName(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE", Locale.getDefault());
        return sdf.format(new Date(timestamp));
    }
}
