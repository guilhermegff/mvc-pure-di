package com.example.mvcpuredi.screens

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.mvcpuredi.R

class UserDetailViewMvc(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?,
): BaseViewMvc<UserDetailViewMvc.Listener>(
    layoutInflater,
    parent,
    R.layout.user_detail,
) {
    interface Listener {
        fun onBackClicked()
    }

    private var toolbar: MyToolbar = findViewById(R.id.toolbar)
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private var txtUserName: TextView = findViewById(R.id.txt_user_name)

    init {
        toolbar.setNavigateUpListener {
            for (listener in listeners) {
                listener.onBackClicked()
            }
        }
        swipeRefreshLayout = findViewById(R.id.swipeRefresh)
        swipeRefreshLayout.isEnabled = false
    }

    fun bindUser(user: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            txtUserName.text = Html.fromHtml(user, Html.FROM_HTML_MODE_LEGACY)
        } else {
            @Suppress("DEPRECATION")
            txtUserName.text = Html.fromHtml(user)
        }
    }

    fun showProgressIndication() {
        swipeRefreshLayout.isRefreshing = true
    }

    fun hideProgressIndication() {
        if (swipeRefreshLayout.isRefreshing) {
            swipeRefreshLayout.isRefreshing = false
        }
    }
}