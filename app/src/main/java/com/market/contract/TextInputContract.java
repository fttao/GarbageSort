package com.market.contract;

import android.annotation.SuppressLint;

import com.market.api.ApiService;
import com.market.model.TrashSearchResponse;
import com.market.mvplibrary.base.BasePresenter;
import com.market.mvplibrary.base.BaseView;
import com.market.mvplibrary.network.NetworkApi;
import com.market.mvplibrary.network.observer.BaseObserver;

/**
 * author: ft
 * created on: 2022/8/5 16:42
 * description:
 */
public class TextInputContract {

    public static class TextInputPresenter extends BasePresenter<ITextInputView> {

        @SuppressLint("CheckResult")
        public void searchResult(String word) {
            ApiService service = NetworkApi.createService(ApiService.class);
            service.searchResult(word).compose(NetworkApi.applySchedulers(new BaseObserver<TrashSearchResponse>() {
                @Override
                public void onSuccess(TrashSearchResponse response) {
                    if (getView() != null) {
                        getView().searchResultResponse(response);
                    }
                }

                @Override
                public void onFailure(Throwable e) {
                    if (getView() != null) {
                        getView().searchResultFailed(e);
                    }
                }
            }));
        }
    }

    public interface ITextInputView extends BaseView {

        void searchResultResponse(TrashSearchResponse wallPaperResponse);

        //获取列表失败返回
        void searchResultFailed(Throwable e);
    }
}
