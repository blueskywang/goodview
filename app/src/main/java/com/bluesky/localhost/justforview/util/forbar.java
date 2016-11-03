package com.bluesky.localhost.justforview.util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by localhost on 2016/10/30.
 */

public class forbar {


    /**
     * 获得状态栏高度
     */
    private static int getStatusBarHeight(Context context) {
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }
}
