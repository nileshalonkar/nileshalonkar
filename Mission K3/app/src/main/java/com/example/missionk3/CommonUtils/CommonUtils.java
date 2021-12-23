package com.example.missionk3.CommonUtils;

import android.app.Activity;

import androidx.recyclerview.widget.LinearLayoutManager;

public class CommonUtils {

    public static LinearLayoutManager verticalRecycleHandle(Activity activity) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        return linearLayoutManager;
    }


    public static LinearLayoutManager horizontalRecycleHandle(Activity activity) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        return linearLayoutManager;
    }


}
