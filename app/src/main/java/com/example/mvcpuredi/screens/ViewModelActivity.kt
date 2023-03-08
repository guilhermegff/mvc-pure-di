package com.example.mvcpuredi.screens

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.mvcpuredi.R
import com.example.mvcpuredi.screens.common.BaseActivity
import com.example.mvcpuredi.screens.common.MyToolbar
import com.example.mvcpuredi.screens.common.ScreensNavigator
import javax.inject.Inject

class ViewModelActivity : BaseActivity() {

    @Inject
    lateinit var screensNavigator: ScreensNavigator

    private lateinit var toolbar: MyToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.view_model)

        toolbar = findViewById(R.id.toolbar)
        toolbar.setNavigateUpListener {
            screensNavigator.navigateBack()
        }
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, ViewModelActivity::class.java)
            context.startActivity(intent)
        }
    }
}