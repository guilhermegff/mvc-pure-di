package com.example.mvcpuredi.screens.common

import androidx.appcompat.app.AppCompatActivity
import com.example.mvcpuredi.*

open class BaseActivity : AppCompatActivity() {
    val appComponent get() = (application as MyApplication).appComponent

    val activityComponent by lazy {
        appComponent.newActivityComponentBuilder()
            .activity(this)
            .build()
    }

    private val presentationComponent by lazy {
        activityComponent.newPresentationComponent(
            //PresentationModule(),
            //UseCasesModule()
        )
    }

    protected val injector get() = presentationComponent
}