package com.example.mvcpuredi.screens

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.mvcpuredi.networking.UsersApi
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserDetailActivity : AppCompatActivity(), UserDetailViewMvc.Listener {
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private lateinit var usersApi: UsersApi
    private lateinit var userId: String
    private lateinit var viewMvc: UserDetailViewMvc

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewMvc = UserDetailViewMvc(LayoutInflater.from(this), null)
        setContentView(viewMvc.rootView)

        val retrofit = Retrofit.Builder().baseUrl("http://10.0.2.2:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient().newBuilder().addInterceptor(HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            }).build()).build()
        usersApi = retrofit.create(UsersApi::class.java)

        userId = intent.extras!!.getString(EXTRA_USER_ID)!!
    }

    override fun onStart() {
        super.onStart()
        viewMvc.registerListener(this)
        fetchUserDetail()
    }

    override fun onStop() {
        super.onStop()
        coroutineScope.coroutineContext.cancelChildren()
        viewMvc.unregisterListener(this)
    }

    private fun fetchUserDetail() {
        coroutineScope.launch {
            viewMvc.showProgressIndication()
            try {
                val response = usersApi.getUser(userId.toInt())
                if (response.isSuccessful && response.body() != null) {
                    val userName = response.body()!!.id
                    viewMvc.bindUser(userName.toString())
                } else {
                    onFetchFailed()
                }
            } catch (t: Throwable) {
                if (t !is CancellationException) {
                    onFetchFailed()
                }
            } finally {
                viewMvc.hideProgressIndication()
            }
        }
    }

    private fun onFetchFailed() {
        supportFragmentManager.beginTransaction().add(ServerErrorDialogFragment.newInstance(), null)
            .commitAllowingStateLoss()
    }

    companion object {
        const val EXTRA_USER_ID = "EXTRA_USER_ID"
        fun start(context: Context, userId: String) {
            val intent = Intent(context, UserDetailActivity::class.java)
            intent.putExtra(EXTRA_USER_ID, userId)
            context.startActivity(intent)
        }
    }

    override fun onBackClicked() {
        onBackPressed()
    }
}