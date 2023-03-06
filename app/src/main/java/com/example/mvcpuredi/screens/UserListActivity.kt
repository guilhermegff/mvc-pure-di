package com.example.mvcpuredi.screens

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.mvcpuredi.User
import com.example.mvcpuredi.networking.UserResponse
import com.example.mvcpuredi.networking.UsersApi
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserListActivity : AppCompatActivity(), UserListViewMvc.Listener {
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private lateinit var usersApi: UsersApi

    private var isDataLoaded = false

    private lateinit var viewMvc: UserListViewMvc

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewMvc = UserListViewMvc(LayoutInflater.from(this), null)
        setContentView(viewMvc.rootView)

        val retrofit = Retrofit.Builder().baseUrl("http://10.0.2.2:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient().newBuilder().addInterceptor(HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            }).build()).build()
        usersApi = retrofit.create(UsersApi::class.java)
    }

    override fun onStart() {
        super.onStart()
        viewMvc.registerListener(this)
        if (!isDataLoaded) {
            fetchUsers()
        }
    }

    override fun onStop() {
        super.onStop()
        coroutineScope.coroutineContext.cancelChildren()
        viewMvc.unregisterListener(this)
    }

    override fun onRefreshClicked() {
        fetchUsers()
    }

    private fun fetchUsers() {
        coroutineScope.launch {
            viewMvc.showProgressIndication()
            try {
                val response = usersApi.getUsers()
                if (response.isSuccessful && response.body() != null) {
                    viewMvc.bindUsers(response.body()!!.map {
                        it.toUser()
                    })
                    isDataLoaded = true
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

    override fun onUserClicked(clickedUser: User) {
        UserDetailActivity.start(this, clickedUser.id.toString())
    }

    private fun UserResponse.toUser() = User(
        id, name, status
    )
}