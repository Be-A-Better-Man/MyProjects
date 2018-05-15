package com.itaem.serpit.newssimple.news.view;



import com.itaem.serpit.newssimple.news.protocol.NewsBean;
import com.itaem.serpit.newssimple.news.protocol.forNewBean.Contentlist;

import java.util.List;


public interface NewsView {

    void showProgress();

    void addNews(List<Contentlist> newsList);

    void hideProgress();

    void showLoadFailMsg();
}
