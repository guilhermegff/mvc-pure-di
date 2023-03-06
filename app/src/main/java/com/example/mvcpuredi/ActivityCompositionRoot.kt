package com.example.mvcpuredi

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.mvcpuredi.screens.ScreensNavigator

class ActivityCompositionRoot(
    private val activity: AppCompatActivity,
    private val appCompositionRoot: AppCompositionRoot,
) {
    val screensNavigator by lazy {
        ScreensNavigator(activity)
    }

    val layoutInflater get() = LayoutInflater.from(activity)
    val fragmentManager get() = activity.supportFragmentManager
    val usersApi get() = appCompositionRoot.usersApi
}