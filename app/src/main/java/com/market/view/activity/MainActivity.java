package com.market.view.activity;

import android.os.Bundle;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.market.R;
import com.market.mvplibrary.base.BaseActivity;

/**
 * author: ft
 * created on: 2022/8/18 14:05
 * description:
 */
public class MainActivity extends BaseActivity {
    private BottomNavigationView bottom_navigation;

    @Override
    public void initData(Bundle savedInstanceState) {
        bottom_navigation = findViewById(R.id.bottom_navigation);
        //获取navController
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //通过setupWithNavController将底部导航和导航控制器进行绑定
        NavigationUI.setupWithNavController(bottom_navigation, navController);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }
}
