package com.itaem.serpit.newssimple.utils;

import com.itaem.serpit.newssimple.commons.Commons;
import com.show.api.ShowApiRequest;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/4/16 0016.
 */

public class NetWorkUtils {
    private final static String TAG = "NetWorkUtils";
     public static void getNetNews( final String type){
         Observable.create(new ObservableOnSubscribe<String>( ) {
             @Override
             public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                 String res = new ShowApiRequest("http://route.showapi.com/109-35", Commons.APPID,Commons.SECRET)
                         .addTextPara("title",type)
                         .post();
                 emitter.onNext(res);

             }
         }).observeOn(AndroidSchedulers.mainThread())
                 .subscribeOn(Schedulers.newThread())
                 .subscribe(new Consumer<String>() {
                     @Override
                     public void accept(String s) throws Exception {
                         LogUtils.d(TAG,s);
                     }
                 });
     }
}
