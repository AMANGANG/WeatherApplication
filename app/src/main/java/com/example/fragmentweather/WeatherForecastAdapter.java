package com.example.fragmentweather;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.List;
//TODO remove spacing
//// TODO fix indentation

    public class WeatherForecastAdapter extends RecyclerView.Adapter<WeatherForecastAdapter.ViewHolder> {
        /*
        // TODO why is this not final? please explain why should we make it final?
          I didn't think at that time to make it final i think we should do it
          ensures that once it's value is  set, it cannot be changed in a different list
         */
        private List<WeatherForecastResponse.Forecast> forecasts;

        public WeatherForecastAdapter(List<WeatherForecastResponse.Forecast> forecasts) {
            this.forecasts = forecasts;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            /*
            //// TODO what do you understand by parent?
            //// TODO what do you understand by attachToRoot false?
            The parent parameter is basically used to provide the context for display like
            in my case recyclerview.
            AttachToRoot false means that  the view that we want to display will  not be immediately attached to the parent view group.
            in case we want to make some changes in it.


             */
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
            return new ViewHolder(view);
        }


        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            WeatherForecastResponse.Forecast forecast = forecasts.get(position);
            holder.textViewDate.setText(forecast.getDateTime());
            holder.textViewTemperature.setText(forecast.getMain().getTemp() + "°C");
            holder.textViewWeatherDescription.setText(forecast.getWeather().get(0).getDescription());
            changeImagesAccordibdtocondition(holder.lottieAnimationView, forecast.getWeather().get(0).getDescription());
        }

        private void changeImagesAccordibdtocondition(LottieAnimationView lottieAnimationView, String condition) {
            switch (condition) {
                //// TODO never use magic literals
                case "Clear Sky":
                case "Sunny":
                case "Clear":
                    lottieAnimationView.setAnimation(R.raw.sun);
                    break;
                case "Clouds":
                case "PartlyClouds":
                case "overcast clouds":
                case "broken clouds":
                case "Mist":
                case "Foggy":
                case "few clouds":
                case "scattered clouds":
                    lottieAnimationView.setAnimation(R.raw.cloud);
                    break;
                case "light rain":
                case "Drizzle":
                case "moderate rain":
                case "Showers":
                case "Heavy Rain":
                case "Rain":
                    lottieAnimationView.setAnimation(R.raw.rain);
                    break;
                case "Light Snow":
                case "Heavy Snow":
                case "Moderate Snow":
                case "Blizzard":
                    lottieAnimationView.setAnimation(R.raw.snow);
                    break;
                default:
                    lottieAnimationView.setAnimation(R.raw.sun);
                    break;
            }
            lottieAnimationView.playAnimation();

        }


        @Override
        public int getItemCount() {
            return forecasts.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            LottieAnimationView lottieAnimationView;
            TextView textViewDate;
            TextView textViewTemperature;
            TextView textViewWeatherDescription;

            public ViewHolder(View itemView) {
                super(itemView);
                lottieAnimationView = itemView.findViewById(R.id.lottieAnimationView2);
                textViewDate = itemView.findViewById(R.id.textViewDate);
                textViewTemperature = itemView.findViewById(R.id.textViewTemperature);
                textViewWeatherDescription = itemView.findViewById(R.id.textViewWeatherDescription);


            }

        }


    }
