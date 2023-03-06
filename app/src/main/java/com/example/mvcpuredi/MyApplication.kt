package com.example.mvcpuredi

import android.app.Application

class MyApplication : Application() {
    lateinit var appCompositionRoot: AppCompositionRoot

    override fun onCreate() {
        appCompositionRoot = AppCompositionRoot()
        super.onCreate()
    }
}