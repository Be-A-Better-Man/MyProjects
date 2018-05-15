package com.itaem.serpit.newssimple.weather.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;


import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.itaem.serpit.newssimple.utils.BDLocationHelper;
import com.itaem.serpit.newssimple.utils.ToolsUtil;
import com.itaem.serpit.newssimple.weather.model.WeatherModel;
import com.itaem.serpit.newssimple.weather.model.WeatherModelImpl;
import com.itaem.serpit.newssimple.weather.protocol.WeatherBean;
import com.itaem.serpit.newssimple.weather.view.WeatherView;

import java.util.List;


public class WeatherPresenterImpl implements WeatherPresenter, WeatherModelImpl.LoadWeatherListener, BDLocationListener {

    private WeatherView mWeatherView;
    private WeatherModel mWeatherModel;
    private Context mContext;
    private  BDLocationHelper helper;

    public WeatherPresenterImpl(Context context, WeatherView weatherView) {
        this.mContext = context;
        this.mWeatherView = weatherView;
        mWeatherModel = new WeatherModelImpl();
    }

    @Override
    public void loadWeatherData() {
        mWeatherView.showProgress();
        if(!ToolsUtil.isNetworkAvailable(mContext)) {
            mWeatherView.hideProgress();
            mWeatherView.showErrorToast("无网络连接");
            return;
        }

         helper = new BDLocationHelper(this,mContext);
        helper.startClient();
       /* WeatherModelImpl.LoadLocationListener listener = new WeatherModelImpl.LoadLocationListener() {
            @Override
            public void onSuccess(String cityName) {
                //定位成功，获取定位城市天气预报
                mWeatherView.setCity(cityName);
                mWeatherModel.loadWeatherData(cityName, WeatherPresenterImpl.this);
            }

            @Override
            public void onFailure(String msg, Exception e) {
                mWeatherView.showErrorToast("定位失败");
                mWeatherView.setCity("湛江");
                mWeatherModel.loadWeatherData("湛江", WeatherPresenterImpl.this);
            }
        };
        //获取定位信息
        mWeatherModel.loadLocation(mContext, listener);*/
    }

    @Override
    public void onSuccess(List<WeatherBean> list) {
        if(list != null && list.size() > 0) {
            WeatherBean todayWeather = list.remove(0);
            mWeatherView.setToday(todayWeather.getDate());
            mWeatherView.setTemperature(todayWeather.getTemperature());
            mWeatherView.setWeather(todayWeather.getWeather());
            mWeatherView.setWind(todayWeather.getWind());
            mWeatherView.setWeatherImage(todayWeather.getImageRes());
        }
        mWeatherView.setWeatherData(list);
        mWeatherView.hideProgress();
        mWeatherView.showWeatherLayout();
    }

    @Override
    public void onFailure(String msg, Exception e) {
        mWeatherView.hideProgress();
        mWeatherView.showErrorToast("获取天气数据失败");
    }


    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        String cityName = bdLocation.getCity();
        Log.d("Location",cityName);
        if (!TextUtils.isEmpty(cityName)){
            mWeatherView.setCity(cityName);
            mWeatherModel.loadWeatherData(cityName, WeatherPresenterImpl.this);
        }else {
            helper.stopClient();
            mWeatherView.showErrorToast("定位失败");
            mWeatherView.setCity("湛江");
            mWeatherModel.loadWeatherData("湛江", WeatherPresenterImpl.this);
        }

    }
}
