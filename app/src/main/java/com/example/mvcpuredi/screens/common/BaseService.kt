package com.example.mvcpuredi.screens.common

import android.app.Service
import com.example.mvcpuredi.MyApplication
import com.example.mvcpuredi.di.service.ServiceModule

abstract class BaseService: Service() {
    private val appComponent get() = (application as MyApplication).appComponent

    val serviceComponent by lazy {
        appComponent.newServiceComponent(ServiceModule(this))
    }
}