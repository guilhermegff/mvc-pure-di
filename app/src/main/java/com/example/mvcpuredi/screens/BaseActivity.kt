package com.example.mvcpuredi.screens

import androidx.appcompat.app.AppCompatActivity
import com.example.mvcpuredi.*

open class BaseActivity : AppCompatActivity() {
    val appCompositionRoot get() = (application as MyApplication).appCompositionRoot

    val activityCompositionRoot by lazy {
        ActivityCompositionRoot(this, appCompositionRoot)
    }

    private val presentationComponent by lazy {
        DaggerPresentationComponent.builder()
            .presentationModule(PresentationModule(activityCompositionRoot))
            .build()
    }

    protected val injector get() = Injector(presentationComponent)
}