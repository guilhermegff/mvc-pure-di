package com.example.mvcpuredi

import android.app.Application
import com.example.mvcpuredi.di.app.AppModule
import com.example.mvcpuredi.di.app.DaggerAppComponent

class MyApplication : Application() {
    val appComponent by lazy {
        DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    override fun onCreate() {
        super.onCreate()
    }
}