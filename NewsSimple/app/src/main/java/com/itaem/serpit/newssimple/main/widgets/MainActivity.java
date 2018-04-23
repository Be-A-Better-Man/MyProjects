package com.itaem.serpit.newssimple.main.widgets;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.itaem.serpit.newssimple.R;
import com.itaem.serpit.newssimple.about.widget.AboutFragment;
import com.itaem.serpit.newssimple.commons.Commons;
import com.itaem.serpit.newssimple.images.widget.ImageFragment;
import com.itaem.serpit.newssimple.main.presenter.MainPresenter;
import com.itaem.serpit.newssimple.main.presenter.MainPresenterImpl;
import com.itaem.serpit.newssimple.main.view.MainView;
import com.itaem.serpit.newssimple.news.widget.fragment.NewsFragment;
import com.itaem.serpit.newssimple.utils.SPUtils;
import com.itaem.serpit.newssimple.weather.widget.WeatherFragment;

import skin.support.SkinCompatManager;
import skin.support.app.SkinCompatActivity;

/**
 * Created by Administrator on 2018/4/16 0016.
 */

public class MainActivity extends AppCompatActivity implements MainView{

    private static final String TAG = "MainActivity";

    FloatingActionButton fb;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private MainPresenter mMainPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //android 6.0权限判定
        judgePermission();



        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        fb = (FloatingActionButton) findViewById(R.id.fb);

        //换肤的逻辑，通过SharedPreferences来记录皮肤的状态
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SPUtils.getBoolean(Commons.SP_KEY_IS_NIGHT)){
                    SkinCompatManager.getInstance().restoreDefaultTheme();
                    SPUtils.putBoolean(Commons.SP_KEY_IS_NIGHT,false);
                }else {
                    SkinCompatManager.getInstance().loadSkin("night",SkinCompatManager.SKIN_LOADER_STRATEGY_BUILD_IN);
                    SPUtils.putBoolean(Commons.SP_KEY_IS_NIGHT,true);
                }

            }
        });
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open,
                R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        setupDrawerContent(mNavigationView);

        mMainPresenter = new MainPresenterImpl(this);

        switch2News();
    }

    private void judgePermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            String[] permissions = {
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE

            };

            if (checkSelfPermission(permissions[0]) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(permissions, 0);
            }
        }
    }

    @Override
    public void switch2News() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new NewsFragment()).commit();
        mToolbar.setTitle(R.string.navigation_news);
    }

    @Override
    public void switch2Images() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new ImageFragment()).commit();
        mToolbar.setTitle(R.string.navigation_images);
    }

    @Override
    public void switch2Weather() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new WeatherFragment()).commit();
        mToolbar.setTitle(R.string.navigation_weather);
    }

    @Override
    public void switch2About() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new AboutFragment()).commit();
        mToolbar.setTitle(R.string.navigation_about);
    }
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        mMainPresenter.switchNavigation(menuItem.getItemId());
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }
}
