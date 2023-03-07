package com.example.mvcpuredi.screens.common

import android.app.Activity
import com.example.mvcpuredi.screens.UserDetailActivity

class ScreensNavigator(private val activity: Activity) {
    fun navigateBack() {
        activity.onBackPressed()
    }

    fun toUserDetail(userId: String) {
        UserDetailActivity.start(activity, userId)
    }
}