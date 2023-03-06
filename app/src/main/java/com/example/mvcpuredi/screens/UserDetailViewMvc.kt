package com.example.mvcpuredi.screens

import android.content.Context
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.mvcpuredi.R

class UserDetailViewMvc(
    private val layoutInflater: LayoutInflater, private val parent: ViewGroup?
) {
    interface Listener {
        fun onBackClicked()
    }

    private lateinit var toolbar: MyToolbar
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var txtUserName: TextView

    val rootView: View = layoutInflater.inflate(R.layout.user_detail, parent, false)

    private val context: Context get() = rootView.context

    private val listeners = HashSet<Listener>()

    init {
        txtUserName = findViewById(R.id.txt_user_name)
        toolbar = findViewById(R.id.toolbar)
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

    private fun <T : View?> findViewById(@IdRes id: Int): T {
        return rootView.findViewById<T>(id)
    }

    fun registerListener(listener: Listener) {
        listeners.add(listener)
    }

    fun unregisterListener(listener: Listener) {
        listeners.remove(listener)
    }
}