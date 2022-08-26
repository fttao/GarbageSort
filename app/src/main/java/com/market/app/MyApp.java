package com.market.app;

import com.market.mvplibrary.BaseApplication;
import com.market.mvplibrary.network.NetworkApi;

import org.litepal.LitePal;

/**
 * author: ft
 * created on: 2022/8/5 16:34
 * description:
 */
public class MyApp extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        NetworkApi.init(new NetworkRequiredInfo(this));

        //数据库初始化
        LitePal.initialize(this);
    }
}
