package com.market.mvplibrary.mvp;

import android.os.Bundle;

import com.market.mvplibrary.base.BaseActivity;
import com.market.mvplibrary.base.BasePresenter;
import com.market.mvplibrary.base.BaseView;

/**
 * author: ft
 * created on: 2022/8/5 15:46
 * description:适用于需要访问网络接口的Activity
 */
public abstract class MvpActivity<P extends BasePresenter> extends BaseActivity {

    protected P mPresenter;

    /**
     * 创建Presenter
     */
    protected abstract P createPresenter();

    @Override
    public void initBeforeView(Bundle savedInstanceState) {
        //创建
        mPresenter = createPresenter();
        //绑定View
        mPresenter.attachView((BaseView) this);
    }

    /**
     * 页面销毁时解除绑定
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
