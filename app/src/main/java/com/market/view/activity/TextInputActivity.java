package com.market.view.activity;

import android.os.Bundle;

import com.market.contract.TextInputContract;
import com.market.model.TrashSearchResponse;
import com.market.mvplibrary.mvp.MvpActivity;

/**
 * author: ft
 * created on: 2022/8/25 16:22
 * description:
 */
public class TextInputActivity extends MvpActivity<TextInputContract.TextInputPresenter> implements TextInputContract.ITextInputView {
    @Override
    protected TextInputContract.TextInputPresenter createPresenter() {
        return new TextInputContract.TextInputPresenter();
    }

    @Override
    public void searchResultResponse(TrashSearchResponse wallPaperResponse) {

    }

    @Override
    public void searchResultFailed(Throwable e) {

    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return 0;
    }
}
