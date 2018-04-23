package com.itaem.serpit.newssimple.news.view;



import com.itaem.serpit.newssimple.news.protocol.NewsBean;
import com.itaem.serpit.newssimple.news.protocol.forNewBean.Contentlist;

import java.util.List;

/**
 * Description :
 * Author : lauren
 * Email  : lauren.liuling@gmail.com
 * Blog   : http://www.liuling123.com
 * Date   : 15/12/18
 */
public interface NewsView {

    void showProgress();

    void addNews(List<Contentlist> newsList);

    void hideProgress();

    void showLoadFailMsg();
}
