package com.itaem.serpit.newssimple.news.protocol.forNewBean;

/**
 * Created by Administrator on 2018/4/17 0017.
 */

public class Showapi_res_body {
    private int ret_code;
    private Pagebean pagebean;
    public void setRet_code(int ret_code) {
        this.ret_code = ret_code;
    }
    public int getRet_code() {
        return ret_code;
    }

    public void setPagebean(Pagebean pagebean) {
        this.pagebean = pagebean;
    }
    public Pagebean getPagebean() {
        return pagebean;
    }
}
