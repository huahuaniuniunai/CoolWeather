package com.example.test;

import android.app.Application;
import android.content.Context;

import org.litepal.LitePal;

/**
 * 需要在manifest.xml文件中初始化一下，声明android:name="com.example.test.MyApplication"，就可以全局获取context
 */
public class MyApplication extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        LitePal.initialize(context);
    }

    public static Context getContext() {
        return context;
    }
}
