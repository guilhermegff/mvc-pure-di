package com.example.mvcpuredi.screens.common

import androidx.appcompat.app.AppCompatActivity
import com.example.mvcpuredi.*
import com.example.mvcpuredi.di.activity.ActivityModule
import com.example.mvcpuredi.di.presentation.PresentationModule
import com.example.mvcpuredi.di.presentation.UseCasesModule

open class BaseActivity : AppCompatActivity() {
    val appComponent get() = (application as MyApplication).appComponent

    val activityComponent by lazy {
        appComponent.newActivityComponent(ActivityModule(this))
    }

    private val presentationComponent by lazy {
        activityComponent.newPresentationComponent(PresentationModule(), UseCasesModule())
    }

    protected val injector get() = presentationComponent
}