package com.market.view.activity;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.market.R;
import com.market.adapter.WallPaperAdapter;
import com.market.model.WallPaperResponse;
import com.market.contract.TestContract;
import com.market.mvplibrary.mvp.MvpActivity;
import com.market.mvplibrary.network.utils.KLog;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends MvpActivity<TestContract.MainPresenter> implements TestContract.IMainView {
    private static final String TAG = "MainActivity";
    private final List<WallPaperResponse.ResBean.VerticalBean> mList = new ArrayList<>();
    private WallPaperAdapter mAdapter;

    @Override
    protected TestContract.MainPresenter createPresenter() {
        return new TestContract.MainPresenter();
    }

    /**
     * 获取壁纸返回
     * @param wallPaperResponse
     */
    @Override
    public void getWallPaper(WallPaperResponse wallPaperResponse) {
        List<WallPaperResponse.ResBean.VerticalBean> vertical = wallPaperResponse.getRes().getVertical();
        if (vertical != null && vertical.size() > 0) {
            mList.clear();
            mList.addAll(vertical);
            mAdapter.notifyDataSetChanged();
        } else {
            showToast("数据为空");
        }
        hideLoadingDialog();
    }

    @Override
    public void getWallPaperFailed(Throwable e) {
        KLog.e(TAG,e.toString());
        showToast("获取列表数据异常，具体日志信息请查看日志");
        hideLoadingDialog();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        //显示加载弹窗
        showLoadingDialog();
        //初始化列表
        initList();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_test;
    }

    /**
     * 初始化列表
     */
    private void initList() {
        RecyclerView rv = findViewById(R.id.rv);
        //配置rv
        mAdapter = new WallPaperAdapter(R.layout.item_wallpaper, mList);
        rv.setLayoutManager(new GridLayoutManager(mActivity,2));
        rv.setAdapter(mAdapter);

        //请求列表数据
        mPresenter.getWallPaper();
    }
}