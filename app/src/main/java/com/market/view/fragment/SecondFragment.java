package com.market.view.fragment;

import android.os.Bundle;

import com.market.contract.SecondContract;
import com.market.model.TrashSearchResponse;
import com.market.mvplibrary.mvp.MvpFragment;

/**
 * author: ft
 * created on: 2022/8/25 15:40
 * description:
 */
public class SecondFragment extends MvpFragment<SecondContract.SecondPresenter> implements SecondContract.ISecondView {
    @Override
    public void searchResultResponse(TrashSearchResponse response) {

    }

    @Override
    public void searchResultFailed(Throwable e) {

    }

    @Override
    protected SecondContract.SecondPresenter createPresent() {
        return null;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return 0;
    }
}
