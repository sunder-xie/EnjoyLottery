package com.enjoylottery.service;

import android.app.Application;
import android.util.Log;

import com.baidu.mapapi.SDKInitializer;
import com.enjoylottery.bean.BallBean;
import com.enjoylottery.tools.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;
import io.rong.imageloader.core.ImageLoader;
import io.rong.imageloader.core.ImageLoaderConfiguration;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * Created by Administrator on 2017/4/5 0005.
 */

public class MapApplication extends Application {
    private static MapApplication myApplication = null;
    private static final String TAG = "JPush";
    List<BallBean> list = new ArrayList<>();

    public static MapApplication getInstence() {
        return myApplication;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "[ExampleApplication] onCreate");
        super.onCreate();
        myApplication = this;
        SDKInitializer.initialize(this);
        RongIM.init(this);
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(getApplicationContext()));
        JPushInterface.setDebugMode(true);    // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);
        if (!SharedPreferencesUtils.getParam(getApplicationContext(),"token","").equals("")) {
            connectRong((String)SharedPreferencesUtils.getParam(getApplicationContext(),"token",""));
        }

    }

    public void connectRong(String token){
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
                    @Override
                    public void onTokenIncorrect() {
                        Log.e("rongyun","onTokenIncorrect");
                    }

                    @Override
                    public void onSuccess(String s) {
                        Log.e("rongyun","onSuccess");
                    }

                    @Override
                    public void onError(RongIMClient.ErrorCode errorCode) {
                        Log.e("rongyun","onError");
                    }
                });
    }


    public List<BallBean> getList() {
        return list;
    }

    public void setList(List<BallBean> list) {
        this.list = list;
    }
}
