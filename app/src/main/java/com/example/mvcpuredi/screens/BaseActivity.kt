package com.example.mvcpuredi.screens

import androidx.appcompat.app.AppCompatActivity
import com.example.mvcpuredi.ActivityCompositionRoot
import com.example.mvcpuredi.MyApplication

open class BaseActivity : AppCompatActivity() {
    val appCompositionRoot get() = (application as MyApplication).appCompositionRoot

    val compositionRoot by lazy {
        ActivityCompositionRoot(this, appCompositionRoot)
    }
}