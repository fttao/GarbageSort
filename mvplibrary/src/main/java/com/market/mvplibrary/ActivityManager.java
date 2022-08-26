package com.market.mvplibrary;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * author: ft
 * created on: 2022/8/3 15:33
 * description: 管理Activity
 */
public class ActivityManager {

    //保存所有创建的Activity
    private List<Activity> activityList = new ArrayList<>();

    /**
     * 添加Activity
     */
    public void addActivity(Activity activity) {
        if (activity != null) {
            activityList.add(activity);
        }
    }

    /**
     * 移除activity
     * @param activity
     */
    public void removeActivity(Activity activity) {
        if (activity != null) {
            activityList.remove(activity);
        }
    }

    /**
     * 关闭所有activity
     */
    public void finishAllActivity() {
        for (Activity activity : activityList) {
            activity.finish();
        }
    }
}
