package com.itaem.serpit.newssimple.images.model;

/**
 * 获取图片
 */

import com.itaem.serpit.newssimple.commons.Urls;
import com.itaem.serpit.newssimple.images.ImageJsonUtils;
import com.itaem.serpit.newssimple.images.protocol.ImageBean;
import com.itaem.serpit.newssimple.utils.OkHttpUtils;

import java.util.List;


public class ImageModelImpl implements ImageModel {

    @Override
    public void loadImageList(final OnLoadImageListListener listener) {
        String url = Urls.IMAGES_URL;
        OkHttpUtils.ResultCallback<String> loadNewsCallback = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                List<ImageBean> iamgeBeanList = ImageJsonUtils.readJsonImageBeans(response);
                listener.onSuccess(iamgeBeanList);
            }

            @Override
            public void onFailure(Exception e) {
                listener.onFailure("load image list failure.", e);
            }
        };
        OkHttpUtils.get(url, loadNewsCallback);
    }

    public interface OnLoadImageListListener {
        void onSuccess(List<ImageBean> list);
        void onFailure(String msg, Exception e);
    }
}
