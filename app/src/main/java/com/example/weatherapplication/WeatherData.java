package com.example.weatherapplication;

import java.util.List;

public class WeatherData {

        private Coord coord;
        private List<Weather> weather;
        private String base;
        private Main main;
        private int visibility;
        private Wind wind;
        private Clouds clouds;
        private long dt;
        private Sys sys;
        private int timezone;
        private int id;
        private String name;
        private int cod;

        public static class Coord {
            private double lon;
            private double lat;

            public double getLon() {
                return lon;
            }

            public double getLat() {
                return lat;
            }
        }

        public static class Weather {
            private int id;
            private String main;
            private String description;
            private String icon;

            public int getId() {
                return id;
            }

            public String getMain() {
                return main;
            }

            public String getDescription() {
                return description;
            }

            public String getIcon() {
                return icon;
            }
        }

        public static class Main {
            private double temp;
            private double feels_like;
            private double temp_min;
            private double temp_max;
            private int pressure;
            private int humidity;

            public double getTemp() {
                return temp;
            }

            public double getFeels_like() {
                return feels_like;
            }

            public double getTemp_min() {
                return temp_min;
            }

            public double getTemp_max() {
                return temp_max;
            }

            public int getPressure() {
                return pressure;
            }

            public int getHumidity() {
                return humidity;
            }
        }

        public static class Wind {
            private double speed;
            private int deg;

            public double getSpeed() {
                return speed;
            }

            public int getDeg() {
                return deg;
            }
        }

        public static class Clouds {
            private int all;

            public int getAll() {
                return all;
            }
        }

        public static class Sys {
            private int type;
            private int id;
            private String country;
            private long sunrise;
            private long sunset;

            public int getType() {
                return type;
            }

            public int getId() {
                return id;
            }

            public String getCountry() {
                return country;
            }

            public long getSunrise() {
                return sunrise;
            }

            public long getSunset() {
                return sunset;
            }
        }

        public Coord getCoord() {
            return coord;
        }

        public List<Weather> getWeather() {
            return weather;
        }

        public String getBase() {
            return base;
        }

        public Main getMain() {
            return main;
        }

        public int getVisibility() {
            return visibility;
        }

        public Wind getWind() {
            return wind;
        }

        public Clouds getClouds() {
            return clouds;
        }

        public long getDt() {
            return dt;
        }

        public Sys getSys() {
            return sys;
        }

        public int getTimezone() {
            return timezone;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getCod() {
            return cod;
        }
    }

