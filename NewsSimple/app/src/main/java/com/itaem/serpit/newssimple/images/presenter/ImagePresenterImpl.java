package com.itaem.serpit.newssimple.images.presenter;



import com.itaem.serpit.newssimple.images.model.ImageModel;
import com.itaem.serpit.newssimple.images.model.ImageModelImpl;
import com.itaem.serpit.newssimple.images.protocol.ImageBean;
import com.itaem.serpit.newssimple.images.view.ImageView;

import java.util.List;


public class ImagePresenterImpl implements ImagePresenter, ImageModelImpl.OnLoadImageListListener {

    private ImageModel mImageModel;
    private ImageView mImageView;

    public ImagePresenterImpl(ImageView imageView) {
        this.mImageModel = new ImageModelImpl();
        this.mImageView = imageView;
    }

    @Override
    public void loadImageList() {
        mImageView.showProgress();
        mImageModel.loadImageList(this);
    }

    @Override
    public void onSuccess(List<ImageBean> list) {
        mImageView.addImages(list);
        mImageView.hideProgress();
    }

    @Override
    public void onFailure(String msg, Exception e) {
        mImageView.hideProgress();
        mImageView.showLoadFailMsg();
    }
}
