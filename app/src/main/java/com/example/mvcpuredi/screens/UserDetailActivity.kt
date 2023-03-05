package com.example.mvcpuredi.screens

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.mvcpuredi.R
import com.example.mvcpuredi.networking.UsersApi
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserDetailActivity : AppCompatActivity() {
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private lateinit var toolbar: MyToolbar
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var txtUserName: TextView
    private lateinit var usersApi: UsersApi
    private lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_detail)
        txtUserName = findViewById(R.id.txt_user_name)

        toolbar = findViewById(R.id.toolbar)
        toolbar.setNavigateUpListener { onBackPressed() }

        swipeRefreshLayout = findViewById(R.id.swipeRefresh)
        swipeRefreshLayout.isEnabled = false

        val retrofit = Retrofit.Builder().baseUrl("http://10.0.2.2:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient().newBuilder().addInterceptor(
                HttpLoggingInterceptor().apply {
                    this.level = HttpLoggingInterceptor.Level.BODY
                }
            ).build())
            .build()
        usersApi = retrofit.create(UsersApi::class.java)

        userId = intent.extras!!.getString(EXTRA_USER_ID)!!
    }

    override fun onStart() {
        super.onStart()
        fetchUserDetail()
    }

    override fun onStop() {
        super.onStop()
        coroutineScope.coroutineContext.cancelChildren()
    }

    private fun fetchUserDetail() {
        coroutineScope.launch {
            showProgressIndication()
            try {
                val response = usersApi.getUser(userId.toInt())
                if (response.isSuccessful && response.body() != null) {
                    val userName = response.body()!!.id
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        txtUserName.text = Html.fromHtml(userName.toString(), Html.FROM_HTML_MODE_LEGACY)
                    } else {
                        @Suppress("DEPRECATION") txtUserName.text = Html.fromHtml(userName.toString())
                    }
                } else {
                    onFetchFailed()
                }
            } catch (t: Throwable) {
                if (t !is CancellationException) {
                    onFetchFailed()
                }
            } finally {
                hideProgressIndication()
            }
        }
    }

    private fun onFetchFailed() {
        supportFragmentManager.beginTransaction().add(ServerErrorDialogFragment.newInstance(), null)
            .commitAllowingStateLoss()
    }

    private fun showProgressIndication() {
        swipeRefreshLayout.isRefreshing = true
    }

    private fun hideProgressIndication() {
        swipeRefreshLayout.isRefreshing = false
    }

    companion object {
        const val EXTRA_USER_ID = "EXTRA_USER_ID"
        fun start(context: Context, userId: String) {
            val intent = Intent(context, UserDetailActivity::class.java)
            intent.putExtra(EXTRA_USER_ID, userId)
            context.startActivity(intent)
        }
    }
}