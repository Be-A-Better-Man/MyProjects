package com.itaem.serpit.newssimple.images.view;


import com.itaem.serpit.newssimple.images.protocol.ImageBean;

import java.util.List;


public interface ImageView {
    void addImages(List<ImageBean> list);
    void showProgress();
    void hideProgress();
    void showLoadFailMsg();
}
