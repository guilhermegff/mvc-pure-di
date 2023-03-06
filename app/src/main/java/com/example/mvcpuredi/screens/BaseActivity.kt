package com.example.mvcpuredi.screens

import androidx.appcompat.app.AppCompatActivity
import com.example.mvcpuredi.MyApplication

open class BaseActivity : AppCompatActivity() {
    val compositionRoot get() = (application as MyApplication).appCompositionRoot
}