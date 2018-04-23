package com.itaem.serpit.newssimple.news.model;

import android.support.annotation.MainThread;

import com.itaem.serpit.newssimple.commons.Commons;
import com.itaem.serpit.newssimple.news.presenter.NewsPresenterImpl;
import com.itaem.serpit.newssimple.news.protocol.NewsBean;
import com.itaem.serpit.newssimple.news.view.NewsView;
import com.itaem.serpit.newssimple.utils.JsonUtils;
import com.itaem.serpit.newssimple.utils.LogUtils;
import com.itaem.serpit.newssimple.utils.OkHttpUtils;
import com.show.api.ShowApiRequest;

import org.reactivestreams.Subscriber;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 新闻Model的真正实现类，此处没有使用原来的网络请求工具类，因为此处的网络请求用了API接口里自带的请求类
 * 另外，这里还使用了RxJava作为线程切换（Android的子线程不允许更新UI，同样主线程也不能请求网络）
 */

public class NewsModelImpl implements NewsModel {
    private final static String TAG = "NewsModelImpl";



    @Override
    public void loadNews(final String  type, final NewsPresenterImpl.NewsResultCallBack callBack,final int page) {

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                String res = new ShowApiRequest("http://route.showapi.com/109-35", Commons.APPID,Commons.SECRET)
                        .addTextPara("title",type)
                        .addTextPara("page",page+"")
                        .post();
                emitter.onNext(res);

            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        LogUtils.d(TAG,s);
                        try{
                            NewsBean bean =  JsonUtils.deserialize(s, NewsBean.class);
                            LogUtils.d(TAG,bean.getShowapi_res_body().getPagebean().getContentlist().get(0).getDesc());
                            callBack.onSuccess(bean);
                        }catch (Exception e){
                            e.printStackTrace();
                            callBack.onFailed();
                        }

                    }
                });


    }


}
