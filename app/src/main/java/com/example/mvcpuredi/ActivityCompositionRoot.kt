package com.example.mvcpuredi

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.mvcpuredi.screens.ScreensNavigator

class ActivityCompositionRoot(
    val activity: AppCompatActivity,
    private val appModule: AppModule,
) {
    val screensNavigator by lazy {
        ScreensNavigator(activity)
    }

    val application get() = appModule.application
    val layoutInflater get() = LayoutInflater.from(activity)
    val fragmentManager get() = activity.supportFragmentManager
    //val usersApi get() = appModule.usersApi
}