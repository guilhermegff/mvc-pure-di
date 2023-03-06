package com.example.mvcpuredi.screens

import androidx.appcompat.app.AppCompatActivity
import com.example.mvcpuredi.ActivityCompositionRoot
import com.example.mvcpuredi.MyApplication
import com.example.mvcpuredi.PresentationCompositionRoot

open class BaseActivity : AppCompatActivity() {
    val appCompositionRoot get() = (application as MyApplication).appCompositionRoot

    val activityCompositionRoot by lazy {
        ActivityCompositionRoot(this, appCompositionRoot)
    }

    protected val compositionRoot by lazy {
        PresentationCompositionRoot(activityCompositionRoot)
    }
}