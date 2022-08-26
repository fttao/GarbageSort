package com.market.mvplibrary.base;

import android.os.Bundle;
import android.view.View;

/**
 * author: ft
 * created on: 2022/8/3 15:42
 * description:UI回调接口
 */
public interface IUiCallback {

    void initBeforeView(Bundle savedInstanceState);

    //初始化试图
    void initData(Bundle savedInstanceState);

    //获取布局Id
    int getLayoutId();
}
