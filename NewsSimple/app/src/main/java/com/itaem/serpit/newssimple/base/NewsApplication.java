package com.itaem.serpit.newssimple.base;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;

import skin.support.SkinCompatManager;
import skin.support.app.SkinCardViewInflater;
import skin.support.constraint.app.SkinConstraintViewInflater;
import skin.support.design.app.SkinMaterialViewInflater;

/**
 * Created by Administrator on 2018/4/19 0019.
 */

public class NewsApplication extends Application {
     public static Context context ;
    @Override
    public void onCreate() {
        super.onCreate();

        context = this;
        SkinCompatManager.withoutActivity(this)                         // 基础控件换肤初始化
                .addInflater(new SkinMaterialViewInflater())            // material design 控件换肤初始化[可选]
                .addInflater(new SkinConstraintViewInflater())          // ConstraintLayout 控件换肤初始化[可选]
                .addInflater(new SkinCardViewInflater())                // CardView v7 控件换肤初始化[可选]
                .setSkinStatusBarColorEnable(true)                     // 关闭状态栏换肤，默认打开[可选]
                .setSkinWindowBackgroundEnable(true)                   // 关闭windowBackground换肤，默认打开[可选]
                .loadSkin();

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }



}
