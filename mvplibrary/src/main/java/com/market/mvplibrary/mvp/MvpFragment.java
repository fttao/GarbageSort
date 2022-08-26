package com.market.mvplibrary.mvp;

import android.os.Bundle;

import com.market.mvplibrary.base.BaseFragment;
import com.market.mvplibrary.base.BasePresenter;
import com.market.mvplibrary.base.BaseView;

/**
 * author: ft
 * created on: 2022/8/5 15:50
 * description:适用于需要访问网络接口的Fragment
 */
public abstract class MvpFragment<P extends BasePresenter> extends BaseFragment {

    protected P mPresenter;

    /**
     * 创建Presenter
     */
    protected abstract P createPresent();

    @Override
    public void initBeforeView(Bundle savedInstanceState) {
        mPresenter = createPresent();
        mPresenter.attachView((BaseView) this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
