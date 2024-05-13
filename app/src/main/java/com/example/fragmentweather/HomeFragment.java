package com.example.fragmentweather;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.airbnb.lottie.LottieAnimationView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private final String currentCityName = "jaipur";
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
    private WeatherRepository weatherRepository;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        temperatureTextView = view.findViewById(R.id.temp);
        maxTempTextView = view.findViewById(R.id.maxTemp);
        minTempTextView = view.findViewById(R.id.minTemp);
        weatherDescriptionTextView = view.findViewById(R.id.typeOfWeather);
        dayTextView = view.findViewById(R.id.day);
        dateTextView = view.findViewById(R.id.date);
        cityNameTextView = view.findViewById(R.id.textView);
        lottieAnimationView = view.findViewById(R.id.lottieAnimationView);
        frame = view.findViewById(R.id.frame);

        weatherRepository = new WeatherRepository();

        weatherViewModel = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);


////TODO no need for this, you could have just passed the query string
        weatherViewModel.getSearchQuery().observe(getViewLifecycleOwner(), query -> {
            if (query != null) {
                fetchWeatherData(query);
                Log.d("Msg", "onCreateView: " + query);
            } else {
                Log.d("Msg", "Queery is null ");
            }
        });
        return view;
    }

    ////TODO this is part of repository. This should be encapsulated from the view
    // i have sifted that part in the repository class
    private void fetchWeatherData(String cityName) {

        Call<WeatherData> call = weatherRepository.getWeatherData(cityName);

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

                        String temperatureText = String.format(getString(R.string.temp), temperature);
                        temperatureTextView.setText(temperatureText);


                        //// TODO use strings.xml
                        String maxTempText = String.format(getString(R.string.max_temp), maxTemp);
                        maxTempTextView.setText(maxTempText);

                        //// TODO use strings.xml
                        String minTempText = String.format(getString(R.string.min_temp), minTemp);
                        minTempTextView.setText(minTempText);

                        weatherDescriptionTextView.setText(condition);
                        //// TODO instead of dayName function should be to get today's day
                        dayTextView.setText(getCurrentDayName(System.currentTimeMillis()));
                        //// TODO bad function naming
                        dateTextView.setText(getCurrentDate());
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
                frame.setBackgroundResource(R.drawable.cloud_background);
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

    private String getCurrentDate() {
        SimpleDateFormat date = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
        return date.format(new Date());
    }

    //// TODO please explain date format EEEE, what is Locale.getDefault()
    //EEEE is basically used to display full name of the day of the week and  Locale.getDefault()
    // is method that returns the language set in your computer like English or german etc.
    private String getCurrentDayName(long timestamp) {
        SimpleDateFormat day = new SimpleDateFormat("EEEE", Locale.getDefault());
        return day.format(new Date(timestamp));
    }
}
