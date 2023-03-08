package com.example.mvcpuredi.di.activity

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.mvcpuredi.screens.common.ScreensNavigator
import com.example.mvcpuredi.screens.common.ScreensNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class ActivityModule {
    @Binds
    @ActivityScope
    abstract fun screensNavigator(screensNavigatorImpl: ScreensNavigatorImpl): ScreensNavigator

    companion object {
        @Provides
        fun layoutInflater(activity: AppCompatActivity): LayoutInflater =
            LayoutInflater.from(activity)

        @Provides
        fun fragmentManager(activity: AppCompatActivity) = activity.supportFragmentManager
    }
}