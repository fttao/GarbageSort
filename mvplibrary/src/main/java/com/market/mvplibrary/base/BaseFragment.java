package com.market.mvplibrary.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.market.mvplibrary.network.NetworkUtils;

/**
 * author: ft
 * created on: 2022/8/5 15:38
 * description:
 */
public abstract class BaseFragment extends Fragment implements IUiCallback {

    protected View rootView;
    protected LayoutInflater mLayoutInflater;
    protected Activity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBeforeView(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mLayoutInflater = inflater;
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutId(), null);
        } else {
            ViewGroup viewGroup = (ViewGroup) rootView.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(rootView);
            }
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData(savedInstanceState);
    }

    /**
     * 绑定
     * @param context
     */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.mActivity = (Activity) context;
        }
    }

    /**
     * 解绑
     */
    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
    }

    @Override
    public void initBeforeView(Bundle savedInstanceState) {

    }

    /**
     * 检查当前是否打开网络
     */
    protected boolean hasNetwork() {
        return (NetworkUtils.isNetWorkAvailable(mActivity));
    }

    /**
     * 进入Activity
     *
     * @param clazz 目标Activity
     */
    protected void gotoActivity(Class<?> clazz) {
        startActivity(new Intent(mActivity, clazz));
    }

    /**
     * Toast消息提示  字符
     *
     * @param c
     */
    protected void showMsg(CharSequence c) {
        Toast.makeText(mActivity, c, Toast.LENGTH_SHORT).show();
    }
}
