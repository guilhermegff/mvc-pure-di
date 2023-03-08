package com.example.mvcpuredi.di.activity

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.mvcpuredi.screens.common.ScreensNavigator
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(
    val activity: AppCompatActivity,
) {
    @Provides
    fun activity() = activity

    @Provides
    fun layoutInflater(): LayoutInflater = LayoutInflater.from(activity)

    @Provides
    @ActivityScope
    fun screensNavigator() = ScreensNavigator(activity)

    @Provides
    fun fragmentManager() = activity.supportFragmentManager
}