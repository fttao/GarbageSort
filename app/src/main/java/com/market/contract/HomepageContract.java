package com.market.contract;

import android.annotation.SuppressLint;

import com.market.api.ApiService;
import com.market.model.TrashNewsResponse;
import com.market.mvplibrary.base.BasePresenter;
import com.market.mvplibrary.base.BaseView;
import com.market.mvplibrary.network.NetworkApi;
import com.market.mvplibrary.network.observer.BaseObserver;

/**
 * author: ft
 * created on: 2022/8/5 16:42
 * description:
 */
public class HomepageContract {

    public static class HomePagePresenter extends BasePresenter<IHomepageView> {

        @SuppressLint("CheckResult")
        public void getTrashNews(Integer num) {
            ApiService service = NetworkApi.createService(ApiService.class);
            service.getTrashNews(num).compose(NetworkApi.applySchedulers(new BaseObserver<TrashNewsResponse>() {
                @Override
                public void onSuccess(TrashNewsResponse response) {
                    if (getView() != null) {
                        getView().getTrashNewsResponse(response);
                    }
                }

                @Override
                public void onFailure(Throwable e) {
                    if (getView() != null) {
                        getView().getTrashNewsFailed(e);
                    }
                }
            }));
        }
    }

    public interface IHomepageView extends BaseView {

        void getTrashNewsResponse(TrashNewsResponse response);

        //获取列表失败返回
        void getTrashNewsFailed(Throwable e);
    }
}
