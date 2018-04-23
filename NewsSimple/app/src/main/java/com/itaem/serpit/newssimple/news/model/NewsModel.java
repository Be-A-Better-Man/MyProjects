package com.itaem.serpit.newssimple.news.model;

import com.itaem.serpit.newssimple.news.presenter.NewsPresenterImpl;

/**
 * Created by Administrator on 2018/4/16 0016.
 */

public interface NewsModel {
    void loadNews(String type,NewsPresenterImpl.NewsResultCallBack callBack,int page);
}
