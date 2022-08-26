package com.market.mvplibrary;

import android.app.Application;
import android.content.Context;

/**
 * author: ft
 * created on: 2022/8/3 15:38
 * description:
 */
public class BaseApplication extends Application {
    private static ActivityManager sActivityManager;
    private static BaseApplication sApplication;
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        //声明Activity管理
        sActivityManager = new ActivityManager();
        sContext = getApplicationContext();
        sApplication = this;
    }

    public static BaseApplication getApplication() {
        return sApplication;
    }

    public static Context getContext() {
        return sContext;
    }

    public static ActivityManager getActivityManager() {
        return sActivityManager;
    }
}
