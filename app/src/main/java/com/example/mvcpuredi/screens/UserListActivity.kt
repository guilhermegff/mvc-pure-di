package com.example.mvcpuredi.screens

import android.os.Bundle
import com.example.mvcpuredi.R
import com.example.mvcpuredi.screens.common.BaseActivity

class UserListActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_frame)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(R.id.frame_content, UserListFragment())
                .commit()
        }
    }
}