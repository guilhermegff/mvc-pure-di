package com.example.mvcpuredi.screens

import androidx.appcompat.app.AppCompatActivity
import com.example.mvcpuredi.*

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