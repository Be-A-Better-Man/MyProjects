package com.itaem.serpit.newssimple.news.widget.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itaem.serpit.newssimple.R;
import com.itaem.serpit.newssimple.commons.Urls;
import com.itaem.serpit.newssimple.news.presenter.NewsPresenter;
import com.itaem.serpit.newssimple.news.presenter.NewsPresenterImpl;
import com.itaem.serpit.newssimple.news.protocol.NewsBean;
import com.itaem.serpit.newssimple.news.protocol.forNewBean.Contentlist;
import com.itaem.serpit.newssimple.news.view.NewsView;
import com.itaem.serpit.newssimple.news.widget.activity.NewsDetailActivity;
import com.itaem.serpit.newssimple.news.widget.adapter.NewsAdapter;
import com.itaem.serpit.newssimple.utils.LogUtils;


import java.util.ArrayList;
import java.util.List;

/**
 * Description : 新闻Fragment
 */
public class NewsListFragment extends Fragment implements NewsView, SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "NewsListFragment";

    private SwipeRefreshLayout mSwipeRefreshWidget;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private NewsAdapter mAdapter;
    private List<Contentlist> mData;
    private NewsPresenter mNewsPresenter;

    private int mType = NewsFragment.NEWS_TYPE_DOMESTIC;
    private int pageIndex = 0;

    public static NewsListFragment newInstance(int type) {
        Bundle args = new Bundle();
        NewsListFragment fragment = new NewsListFragment();
        args.putInt("type", type);
        fragment.setArguments(args);
        return fragment;
    }


    /*Fragment的初始化先获得P层的示例*/
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNewsPresenter = new NewsPresenterImpl(this);
        mType = getArguments().getInt("type");
        Log.d(TAG,mType+"");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newslist, null);

        //初始化控件
        mSwipeRefreshWidget = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_widget);
        mSwipeRefreshWidget.setColorSchemeResources(R.color.primary,
                R.color.primary_dark, R.color.primary_light,
                R.color.accent);
        mSwipeRefreshWidget.setOnRefreshListener(this);

        mRecyclerView = (RecyclerView)view.findViewById(R.id.recycle_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new NewsAdapter(getActivity().getApplicationContext());
        mAdapter.setOnItemClickListener(mOnItemClickListener);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(mOnScrollListener);

        //加载数据
        onRefresh();
        return view;
    }

    //滑动的监听，实现加载更多的效果
    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {

        private int lastVisibleItem;

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && lastVisibleItem + 1 == mAdapter.getItemCount()
                    && mAdapter.isShowFooter()) {
                //加载更多
                LogUtils.d(TAG, "loading more data");
                mNewsPresenter.loadNews(mType, pageIndex + Urls.PAZE_SIZE);
            }
        }
    };

    private NewsAdapter.OnItemClickListener mOnItemClickListener = new NewsAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
           Contentlist bean =  mData.get(position);
           String url  =  bean.getLink();

           String title  = bean.getTitle();
           String img = "";

           //由于图片有多张，选取其中一张展示
           if (bean.getHavePic()){
               img = bean.getImageurls().get(0).getUrl();
           }

            /*
            * 跳转到新闻详情页
            * title,新闻的标题
            * img,图片的url
            * url,新闻的链接*/
           Intent intent =  new Intent(getActivity(), NewsDetailActivity.class);
           intent.putExtra("title",title);
           intent.putExtra("img",img);
           intent.putExtra("url",url);
           startActivity(intent);
        }
    };

    @Override
    public void showProgress() {
        mSwipeRefreshWidget.setRefreshing(true);
    }

    @Override
    public void addNews(List<Contentlist> newsList) {
        mAdapter.isShowFooter(true);
        if(mData == null) {
            mData = new ArrayList<>();
        }
        LogUtils.d(TAG,"list size : "+ newsList.size());
        mData.addAll(newsList);
        if(pageIndex == 0) {
            mAdapter.setmDate(mData);
        } else {
            //如果没有更多数据了,则隐藏footer布局
            if(newsList == null || newsList.size() == 0) {
                mAdapter.isShowFooter(false);
            }
            mAdapter.notifyDataSetChanged();
        }
        pageIndex += Urls.PAZE_SIZE;
    }


    @Override
    public void hideProgress() {
        mSwipeRefreshWidget.setRefreshing(false);
    }

    @Override
    public void showLoadFailMsg() {
        if(pageIndex == 0) {
            mAdapter.isShowFooter(false);
            mAdapter.notifyDataSetChanged();
        }
        View view = getActivity() == null ? mRecyclerView.getRootView() : getActivity().findViewById(R.id.drawer_layout);
        Snackbar.make(view, getString(R.string.load_fail), Snackbar.LENGTH_SHORT).show();
    }



    /*刷新的逻辑*/
    @Override
    public void onRefresh() {
        pageIndex = 0;
        if(mData != null) {
            mData.clear();
        }
        mNewsPresenter.loadNews(mType, pageIndex);
    }

}
