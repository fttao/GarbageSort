package com.market.mvplibrary.base;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.market.mvplibrary.BaseApplication;
import com.market.mvplibrary.R;

import java.util.Objects;

/**
 * author: ft
 * created on: 2022/8/3 15:51
 * description:基类Activity
 */
public abstract class BaseActivity extends AppCompatActivity implements IUiCallback {

    //Activity上下文
    protected Activity mActivity;
    //弹窗
    private Dialog mDialog;

    private static final int FAST_CLICK_DELAY_TIME = 500;
    private static long lastClickTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //绑定试图
        initBeforeView(savedInstanceState);
        //获取Activity的上下文
        mActivity = this;
        BaseApplication.getActivityManager().addActivity(this);
        //绑定试图XML
        if (getLayoutId() > 0) {
            setContentView(getLayoutId());
        }
        initData(savedInstanceState);
    }

    @Override
    public void initBeforeView(Bundle savedInstanceState) {

    }

    /**
     * Toast消息
     * @param cs
     */
    protected void showToast(CharSequence cs) {
        Toast.makeText(mActivity, cs, Toast.LENGTH_SHORT).show();
    }

    /**
     * Toast字符
     * @param i
     */
    protected void showToast(int i) {
        Toast.makeText(mActivity, i, Toast.LENGTH_SHORT).show();
    }

    /**
     * 加载弹窗
     */
    protected void showLoadingDialog() {
        if (mDialog == null) {
            mDialog = new Dialog(mActivity, R.style.loading_dialog);
        }
        mDialog.setContentView(R.layout.dialog_loading);
        mDialog.setCancelable(false);
        Objects.requireNonNull(mDialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        mDialog.show();
    }

    /**
     * 隐藏弹窗
     */
    protected void hideLoadingDialog() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
        mDialog = null;
    }

    /**
     * 返回  不需要参数
     */
    protected void Back() {
        mActivity.finish();
        if (!isFastClick()) {
            mActivity.finish();
        }
    }

    /**
     * 返回 toolbar控键点击
     * @param toolbar
     */
    protected void goBack(Toolbar toolbar) {
        toolbar.setNavigationOnClickListener(v ->  {
            mActivity.finish();
            if (!isFastClick()) {
                mActivity.finish();
            }
        });
    }
    /**
     *两次点击间隔不能少于500ms
     * @return
     */
    protected boolean isFastClick(){
        boolean flag = true;
        long currentClickTime = System.currentTimeMillis();
        if ((currentClickTime - lastClickTime) >= FAST_CLICK_DELAY_TIME) {
            flag = false;
        }
        lastClickTime = currentClickTime;
        return flag;
    }
}
