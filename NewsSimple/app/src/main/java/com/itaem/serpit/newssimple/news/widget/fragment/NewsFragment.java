package com.itaem.serpit.newssimple.news.widget.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.itaem.serpit.newssimple.R;

import java.util.ArrayList;
import java.util.List;


public class NewsFragment extends Fragment {

    public static final int NEWS_TYPE_DOMESTIC = 0;
    public static final int NEWS_TYPE_INTERNATIONAL = 1;
    public static final int NEWS_TYPE_SPORTS = 2;
    public static final int NEWS_TYPE_ENTERTAINMENT = 3;
    public static final int NEWS_TYPE_TECHNOLOGY = 4;
    public static final int NEWS_TYPE_INTERNET = 5;
    public static final int NEWS_TYPE_MILITARY = 6;
    public static final int NEWS_TYPE_ECONOMICS = 7;



    private TabLayout mTablayout;
    private ViewPager mViewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, null);
        mTablayout = (TabLayout) view.findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mViewPager.setOffscreenPageLimit(3);
        setupViewPager(mViewPager);
        mTablayout.addTab(mTablayout.newTab().setText(R.string.domestic));
        mTablayout.addTab(mTablayout.newTab().setText(R.string.international));
        mTablayout.addTab(mTablayout.newTab().setText(R.string.sports));
        mTablayout.addTab(mTablayout.newTab().setText(R.string.entertainment));
        mTablayout.addTab(mTablayout.newTab().setText(R.string.technology));
        mTablayout.addTab(mTablayout.newTab().setText(R.string.internet));
        mTablayout.addTab(mTablayout.newTab().setText(R.string.military));
        mTablayout.addTab(mTablayout.newTab().setText(R.string.economics));
        mTablayout.setupWithViewPager(mViewPager);
        mTablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        return view;
    }

    private void setupViewPager(ViewPager mViewPager) {
        //Fragment中嵌套使用Fragment一定要使用getChildFragmentManager(),否则会有问题
        MyPagerAdapter adapter = new MyPagerAdapter(getChildFragmentManager());
        adapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_DOMESTIC), getString(R.string.domestic));
        adapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_INTERNATIONAL), getString(R.string.international));
        adapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_SPORTS), getString(R.string.sports));
        adapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_ENTERTAINMENT), getString(R.string.entertainment));
        adapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_TECHNOLOGY), getString(R.string.technology));
        adapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_INTERNET), getString(R.string.internet));
        adapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_MILITARY), getString(R.string.military));
        adapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_ECONOMICS), getString(R.string.economics));
        mViewPager.setAdapter(adapter);
    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
