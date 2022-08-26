package com.market.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.gson.Gson;
import com.market.R;
import com.market.adapter.TrashNewsAdapter;
import com.market.contract.HomepageContract;
import com.market.model.TrashNewsResponse;
import com.market.mvplibrary.base.BaseFragment;
import com.market.mvplibrary.base.BasePresenter;
import com.market.mvplibrary.mvp.MvpFragment;
import com.market.utils.AppStartUpUtils;
import com.market.utils.Constant;
import com.market.utils.NewsHelper;
import com.market.view.activity.MainActivity;
import com.market.view.activity.TextInputActivity;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * author: ft
 * created on: 2022/8/18 16:56
 * description:
 */
public class HomepageFragment extends MvpFragment<HomepageContract.HomePagePresenter> implements HomepageContract.IHomepageView {

    private static final String TAG = "HomepageFragment";
    private Banner banner;
    private RecyclerView rvNews;
    private List<TrashNewsResponse.NewslistBean> mList = new ArrayList<>();
    private TrashNewsAdapter mAdapter;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private AppBarLayout appBarLayout;

    @Override
    public void initData(Bundle savedInstanceState) {
        initView();
    }

    /**
     * 页面初始化
     */
    private void initView() {
        banner = mActivity.findViewById(R.id.banner);
        collapsingToolbarLayout = mActivity.findViewById(R.id.toolbar_layout);
        appBarLayout = mActivity.findViewById(R.id.appbar_layout);
        rvNews = mActivity.findViewById(R.id.rv_news);

        //伸缩偏移量监听
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0 && !isShow) {//收缩时
                    collapsingToolbarLayout.setTitle("垃圾分类");
                    isShow = true;
                } else {//展开时
                    collapsingToolbarLayout.setTitle("");
                    isShow = false;
                }
            }
        });
        //设置列表
        mAdapter = new TrashNewsAdapter(R.layout.item_trash_new_rv, mList);
        mAdapter.setOnItemChildClickListener((adapter, view , position) -> {
            //跳转到新闻详情页面
            showMsg("" + position);
        });
        rvNews.setLayoutManager(new LinearLayoutManager(mActivity));
        rvNews.setAdapter(mAdapter);

        if (hasNetwork()) {//有网络
            if (AppStartUpUtils.isTodayFirstStartApp(mActivity)) {
                //今天第一次启动
                //请求垃圾分类新闻数据
                mPresenter.getTrashNews(10);
            } else {
                //今天后续启动
                //读取本地数据库数据
                List<TrashNewsResponse.NewslistBean> list = NewsHelper.queryAllNews();
                if (list.size() <= 0) {
                    mPresenter.getTrashNews(10);
                } else {
                    showBanner(list);//轮播显示
                    showList(list);//新闻列表显示
                }
            }
        } else {
            //加载默认数据
            TrashNewsResponse response = new Gson().fromJson(Constant.LOCAL_NEWS_DATA, TrashNewsResponse.class);
            mList.clear();
            mList.addAll(response.getNewslist());
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_homepage;
    }

    @Override
    public void getTrashNewsResponse(TrashNewsResponse response) {
        if (response.getCode() == Constant.SUCCESS_CODE) {
            List<TrashNewsResponse.NewslistBean> list = response.getNewslist();
            if (list.size() > 0) {
                //数据显示
                showBanner(list);//轮播显示
                showList(list);//新闻列表显示
                //保存新闻数据
                NewsHelper.saveNews(list);
            } else {
                showMsg("垃圾分类新闻为空");
            }
        } else {
            showMsg(response.getMsg());
        }
    }

    @Override
    public void getTrashNewsFailed(Throwable e) {
        Log.d(TAG, "获取垃圾分类新闻失败：" + e.toString());
    }

    @Override
    protected HomepageContract.HomePagePresenter createPresent() {
        return new HomepageContract.HomePagePresenter();
    }

    /**
     * 显示新闻列表
     *
     * @param list
     */
    private void showList(List<TrashNewsResponse.NewslistBean> list) {
        mList.clear();
        mList.addAll(list);
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 显示轮播图
     *
     * @param list
     */
    public void showBanner(List<TrashNewsResponse.NewslistBean> list) {
        banner.setAdapter(new BannerImageAdapter<TrashNewsResponse.NewslistBean>(list) {
            @Override
            public void onBindView(BannerImageHolder holder, TrashNewsResponse.NewslistBean data, int position, int size) {
                //显示轮播图片
                Glide.with(holder.itemView)
                        .load(data.getPicUrl())
                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                        .into(holder.imageView);
            }
        })
                .addBannerLifecycleObserver(this)//添加生命周期观察者
                .setIndicator(new CircleIndicator(mActivity));
    }

    /**
     * 进入文字输入页面
     */
    public void jumpTextInput(View view) {
        gotoActivity(TextInputActivity.class);
    }
}
