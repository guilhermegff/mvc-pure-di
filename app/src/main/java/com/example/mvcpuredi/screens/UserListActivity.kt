package com.example.mvcpuredi.screens

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.mvcpuredi.User
import com.example.mvcpuredi.networking.UserResponse
import com.example.mvcpuredi.networking.UsersApi
import com.example.mvcpuredi.usecases.FetchUsersUseCase
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserListActivity : AppCompatActivity(), UserListViewMvc.Listener {
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private var isDataLoaded = false

    private lateinit var viewMvc: UserListViewMvc

    private lateinit var fetchUsersUseCase: FetchUsersUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewMvc = UserListViewMvc(LayoutInflater.from(this), null)
        setContentView(viewMvc.rootView)
        fetchUsersUseCase = FetchUsersUseCase()
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
                val result = fetchUsersUseCase.fetchUsers()
                when(result) {
                    is FetchUsersUseCase.Result.Success -> {
                        viewMvc.bindUsers(result.users)
                        isDataLoaded = true
                    }
                    is FetchUsersUseCase.Result.Failure -> onFetchFailed()
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
}