package com.itaem.serpit.newssimple.weather.model;

import android.content.Context;


public interface WeatherModel {

    void loadWeatherData(String cityName, WeatherModelImpl.LoadWeatherListener listener);

    void loadLocation(Context context, WeatherModelImpl.LoadLocationListener listener);

}
