package com.itaem.serpit.newssimple.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.itaem.serpit.newssimple.base.NewsApplication;



public class SPUtils {
    private static SharedPreferences sp = NewsApplication.context.getSharedPreferences("sp", Context.MODE_PRIVATE);
    private static SharedPreferences.Editor editor = sp.edit();

    public static void putBoolean(String key,boolean value){
        editor.putBoolean(key,value);
        editor.commit();
    }

    public static boolean getBoolean(String key){
        return sp.getBoolean(key,false);

    }

}
