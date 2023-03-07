package com.example.mvcpuredi.screens.common

import androidx.appcompat.app.AppCompatActivity
import com.example.mvcpuredi.*
import com.example.mvcpuredi.di.activity.ActivityModule
import com.example.mvcpuredi.di.activity.DaggerActivityComponent
import com.example.mvcpuredi.di.presentation.PresentationModule

open class BaseActivity : AppCompatActivity() {
    val appComponent get() = (application as MyApplication).appComponent

    val activityComponent by lazy {
        DaggerActivityComponent.builder()
            .appComponent(appComponent)
            .activityModule(ActivityModule(this))
            .build()
    }

    private val presentationComponent by lazy {
        activityComponent.newPresentationComponent(PresentationModule())
    }

    protected val injector get() = presentationComponent
}