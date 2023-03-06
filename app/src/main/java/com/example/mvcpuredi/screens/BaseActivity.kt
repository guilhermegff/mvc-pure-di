package com.example.mvcpuredi.screens

import androidx.appcompat.app.AppCompatActivity
import com.example.mvcpuredi.ActivityCompositionRoot
import com.example.mvcpuredi.Injector
import com.example.mvcpuredi.MyApplication
import com.example.mvcpuredi.PresentationCompositionRoot

open class BaseActivity : AppCompatActivity() {
    val appCompositionRoot get() = (application as MyApplication).appCompositionRoot

    val activityCompositionRoot by lazy {
        ActivityCompositionRoot(this, appCompositionRoot)
    }

    private val compositionRoot by lazy {
        PresentationCompositionRoot(activityCompositionRoot)
    }

    protected val injector get() = Injector(compositionRoot)
}