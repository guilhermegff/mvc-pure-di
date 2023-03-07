package com.example.mvcpuredi.screens.common

import androidx.appcompat.app.AppCompatActivity
import com.example.mvcpuredi.di.activity.ActivityScope
import com.example.mvcpuredi.screens.UserDetailActivity
import javax.inject.Inject

@ActivityScope
class ScreensNavigator @Inject constructor(private val activity: AppCompatActivity) {
    fun navigateBack() {
        activity.onBackPressed()
    }

    fun toUserDetail(userId: String) {
        UserDetailActivity.start(activity, userId)
    }
}