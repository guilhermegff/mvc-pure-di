package com.example.mvcpuredi.screens

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.mvcpuredi.User
import com.example.mvcpuredi.usecases.FetchUsersUseCase
import kotlinx.coroutines.*

class UserListActivity : AppCompatActivity(), UserListViewMvc.Listener {
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private var isDataLoaded = false
    private lateinit var viewMvc: UserListViewMvc
    private lateinit var fetchUsersUseCase: FetchUsersUseCase
    private lateinit var dialogsNavigator: DialogsNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewMvc = UserListViewMvc(LayoutInflater.from(this), null)
        setContentView(viewMvc.rootView)
        fetchUsersUseCase = FetchUsersUseCase()
        dialogsNavigator = DialogsNavigator(supportFragmentManager)
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
                when (result) {
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
        dialogsNavigator.showServerErrorDialog()
    }

    override fun onUserClicked(clickedUser: User) {
        UserDetailActivity.start(this, clickedUser.id.toString())
    }
}