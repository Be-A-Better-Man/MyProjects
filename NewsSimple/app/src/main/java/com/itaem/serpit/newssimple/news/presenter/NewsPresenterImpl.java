package com.itaem.serpit.newssimple.news.presenter;


import com.itaem.serpit.newssimple.news.model.NewsModel;
import com.itaem.serpit.newssimple.news.model.NewsModelImpl;

import com.itaem.serpit.newssimple.news.protocol.NewsBean;

import com.itaem.serpit.newssimple.news.view.NewsView;


public class NewsPresenterImpl implements NewsPresenter {
    private static final int NEWS_TYPE_DOMESTIC = 0;
    private static final int NEWS_TYPE_INTERNATIONAL = 1;
    private static final int NEWS_TYPE_SPORTS = 2;
    private static final int NEWS_TYPE_ENTERTAINMENT = 3;
    private static final int NEWS_TYPE_TECHNOLOGY = 4;
    private static final int NEWS_TYPE_INTERNET = 5;
    private static final int NEWS_TYPE_MILITARY = 6;
    private static final int NEWS_TYPE_ECONOMICS = 7;
    private NewsModel model = new NewsModelImpl();
    private NewsView newsView;
    public NewsPresenterImpl(NewsView newsView){
        this.newsView = newsView;
    }

    /*加载新闻列表*/
    @Override
    public void loadNews(int type, int page) {
        String requestType  ;
        if(page == 0) {
            newsView.showProgress();
        }
        switch (type){
            case NEWS_TYPE_DOMESTIC:
                requestType = "国内";
                break;
            case NEWS_TYPE_INTERNATIONAL:
                requestType = "国际";
                break;
            case NEWS_TYPE_SPORTS:
                requestType = "体育";
                break;
            case NEWS_TYPE_ENTERTAINMENT:
                requestType = "娱乐";
                break;
            case NEWS_TYPE_TECHNOLOGY:
                requestType = "科技";
                break;
            case NEWS_TYPE_INTERNET:
                requestType = "互联网";
                break;
            case NEWS_TYPE_MILITARY:
                requestType = "军事";
                break;
            case NEWS_TYPE_ECONOMICS:
                requestType = "财经";
                break;
            default:
                requestType = "国内";
                break;
        }

        //P层调用M层的逻辑，来获取数据再传回给V层
        model.loadNews(requestType, new NewsResultCallBack() {
            @Override
            public void onSuccess(NewsBean bean) {

                newsView.hideProgress();
                newsView.addNews(bean.getShowapi_res_body().getPagebean().getContentlist());
            }

            @Override
            public void onFailed() {
                newsView.hideProgress();
                newsView.showLoadFailMsg();
            }
        },page);


    }

    public interface NewsResultCallBack{
        void onSuccess(NewsBean bean);
        void onFailed();
    }
}
