package com.market.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.market.R;
import com.market.mvplibrary.base.BaseActivity;

/**
 * author: ft
 * created on: 2022/8/25 14:10
 * description:
 */
public class ContActivity extends BaseActivity {

    private TextView tv_title;

    @Override
    public void initData(Bundle savedInstanceState) {
        initView();
    }

    /**
     * 初始化
     */
    private void initView() {
        tv_title = findViewById(R.id.tv_title);//标题
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_cont;
    }

    /**
     * 返回
     */
    public void back(View view) {
        finish();
    }
}
