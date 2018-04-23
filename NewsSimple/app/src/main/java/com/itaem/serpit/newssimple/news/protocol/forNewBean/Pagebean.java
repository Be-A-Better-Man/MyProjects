package com.itaem.serpit.newssimple.news.protocol.forNewBean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/17 0017.
 */

public class Pagebean {

    private int allPages;
    private List<Contentlist> contentlist;
    private int currentPage;
    private int allNum;
    private int maxResult;
    public void setAllPages(int allPages) {
        this.allPages = allPages;
    }
    public int getAllPages() {
        return allPages;
    }

    public void setContentlist(List<Contentlist> contentlist) {
        this.contentlist = contentlist;
    }
    public List<Contentlist> getContentlist() {
        return contentlist;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    public int getCurrentPage() {
        return currentPage;
    }

    public void setAllNum(int allNum) {
        this.allNum = allNum;
    }
    public int getAllNum() {
        return allNum;
    }

    public void setMaxResult(int maxResult) {
        this.maxResult = maxResult;
    }
    public int getMaxResult() {
        return maxResult;
    }
}
