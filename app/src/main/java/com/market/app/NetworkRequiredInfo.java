package com.market.app;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.market.mvplibrary.network.INetworkRequiredInfo;
import com.scwang.smartrefresh.header.BuildConfig;

/**
 * author: ft
 * created on: 2022/8/5 16:09
 * description:网络访问信息
 */
public class NetworkRequiredInfo implements INetworkRequiredInfo {
    private Application mApplication;

    public NetworkRequiredInfo(Application application) {
        this.mApplication = application;
    }

    /**
     * 版本名
     */
    @Override
    public String getAppVersionName() {
//        return BuildConfig.VERSION_NAME;
        PackageManager pm = mApplication.getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(
                    mApplication.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return "未知版本名";
        }
    }

    /**
     * 版本号
     */
    @Override
    public String getAppVersionCode() {
//        return String.valueOf(BuildConfig.VERSION_CODE);
        PackageManager pm = mApplication.getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(
                    mApplication.getPackageName(), 0);
            return String.valueOf(packageInfo.versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            return "未知版本号";
        }
    }

    /**
     * 是否为debug
     */
    @Override
    public boolean isDebug() {
        return BuildConfig.DEBUG;
    }

    /**
     * 应用全局上下文
     */
    @Override
    public Application getApplicationContext() {
        return mApplication;
    }
}
