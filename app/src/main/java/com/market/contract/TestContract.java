package com.market.contract;

import android.annotation.SuppressLint;

import com.market.api.ApiService;
import com.market.model.WallPaperResponse;
import com.market.mvplibrary.base.BasePresenter;
import com.market.mvplibrary.base.BaseView;
import com.market.mvplibrary.network.NetworkApi;
import com.market.mvplibrary.network.observer.BaseObserver;

/**
 * author: ft
 * created on: 2022/8/5 16:42
 * description:
 */
public class TestContract {

    public static class MainPresenter extends BasePresenter<IMainView> {

        @SuppressLint("CheckResult")
        public void getWallPaper() {
            ApiService service = NetworkApi.createService(ApiService.class);
            service.getWallPaper().compose(NetworkApi.applySchedulers(new BaseObserver<WallPaperResponse>() {
                @Override
                public void onSuccess(WallPaperResponse wallPaperResponse) {
                    if (getView() != null) {
                        getView().getWallPaper(wallPaperResponse);
                    }
                }

                @Override
                public void onFailure(Throwable e) {
                    if (getView() != null) {
                        getView().getWallPaperFailed(e);
                    }
                }
            }));
        }
    }

    public interface IMainView extends BaseView {

        void getWallPaper(WallPaperResponse wallPaperResponse);

        //获取列表失败返回
        void getWallPaperFailed(Throwable e);
    }
}
