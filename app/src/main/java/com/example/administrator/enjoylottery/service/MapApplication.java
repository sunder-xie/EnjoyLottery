package com.example.administrator.enjoylottery.service;

import android.app.Application;
import android.util.Log;

import com.baidu.mapapi.SDKInitializer;

import cn.jpush.android.api.JPushInterface;
import io.rong.imageloader.core.ImageLoader;
import io.rong.imageloader.core.ImageLoaderConfiguration;
import io.rong.imkit.RongIM;

/**
 * Created by Administrator on 2017/4/5 0005.
 */

public class MapApplication extends Application {
    private static final String TAG = "JPush";
    @Override
    public void onCreate() {
        Log.d(TAG, "[ExampleApplication] onCreate");
        super.onCreate();
        SDKInitializer.initialize(this);
        RongIM.init(this);
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(getApplicationContext()));

        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush

//        if (SharedPreferencesUtils.getParam(this,"firstString","").equals("success")){
//            Intent intent = new Intent(this, HomeActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
//        }else {
//            Intent intent = new Intent(this, MainActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
//        }
    }
}
