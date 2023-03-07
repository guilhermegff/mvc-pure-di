package com.example.mvcpuredi.di.activity

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.mvcpuredi.di.app.AppModule
import com.example.mvcpuredi.screens.common.ScreensNavigator

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