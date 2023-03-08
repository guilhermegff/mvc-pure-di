package com.example.mvcpuredi.screens.common

import androidx.appcompat.app.AppCompatActivity
import com.example.mvcpuredi.screens.UserDetailActivity
import javax.inject.Inject

class ScreensNavigatorImpl @Inject constructor(
    private val activity: AppCompatActivity
) : ScreensNavigator {
    override fun navigateBack() {
        activity.onBackPressed()
    }

    override fun toUserDetail(userId: String) {
        UserDetailActivity.start(activity, userId)
    }
}