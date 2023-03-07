package com.example.mvcpuredi

import android.app.Application

class MyApplication : Application() {
    val appComponent by lazy {
        DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    override fun onCreate() {
        super.onCreate()
    }
}