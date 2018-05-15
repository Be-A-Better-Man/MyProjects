package com.itaem.serpit.newssimple.weather.view;


import com.itaem.serpit.newssimple.weather.protocol.WeatherBean;

import java.util.List;


public interface WeatherView {
    void showProgress();
    void hideProgress();
    void showWeatherLayout();
    void showErrorToast(String msg);

    void setCity(String city);
    void setToday(String data);
    void setTemperature(String temperature);
    void setWind(String wind);
    void setWeather(String weather);
    void setWeatherImage(int res);
    void setWeatherData(List<WeatherBean> lists);

}
