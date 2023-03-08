package com.example.mvcpuredi.screens

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvcpuredi.R
import com.example.mvcpuredi.screens.common.BaseActivity
import com.example.mvcpuredi.screens.common.MyToolbar
import com.example.mvcpuredi.screens.common.ScreensNavigator
import com.example.mvcpuredi.screens.common.ViewModelFactory
import javax.inject.Inject

class ViewModelActivity : BaseActivity() {

    @Inject
    lateinit var screensNavigator: ScreensNavigator

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var myViewModel: MyViewModel
    private lateinit var myOtherViewModel: MyOtherViewModel

    private lateinit var toolbar: MyToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.view_model)

        toolbar = findViewById(R.id.toolbar)
        toolbar.setNavigateUpListener {
            screensNavigator.navigateBack()
        }

        myViewModel = ViewModelProvider(this, viewModelFactory).get(MyViewModel::class.java)
        myOtherViewModel = ViewModelProvider(this, viewModelFactory).get(MyOtherViewModel::class.java)

        myViewModel.users.observe(this, Observer {
            users -> Toast.makeText(this, "Fecthed ${users.size}", Toast.LENGTH_SHORT).show()
        })
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, ViewModelActivity::class.java)
            context.startActivity(intent)
        }
    }
}