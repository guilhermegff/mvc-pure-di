package com.example.mvcpuredi.screens.common

import androidx.appcompat.app.AppCompatActivity
import com.example.mvcpuredi.*
import com.example.mvcpuredi.di.Injector
import com.example.mvcpuredi.di.activity.ActivityModule
import com.example.mvcpuredi.di.activity.DaggerActivityComponent
import com.example.mvcpuredi.di.presentation.DaggerPresentationComponent
import com.example.mvcpuredi.di.presentation.PresentationModule

open class BaseActivity : AppCompatActivity() {
    val appCompositionRoot get() = (application as MyApplication).appComponent

    val activityComponent by lazy {
        DaggerActivityComponent.builder().activityModule(ActivityModule(this, appCompositionRoot))
            .build()
    }

    private val presentationComponent by lazy {
        DaggerPresentationComponent.builder()
            .presentationModule(PresentationModule(activityComponent)).build()
    }

    protected val injector get() = Injector(presentationComponent)
}