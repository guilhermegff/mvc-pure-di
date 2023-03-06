package com.example.mvcpuredi.screens

import android.app.Activity

class ScreensNavigator(private val activity: Activity) {
    fun navigateBack() {
        activity.onBackPressed()
    }

    fun toUserDetail(userId: String) {
        UserDetailActivity.start(activity, userId)
    }
}