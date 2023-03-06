package com.example.mvcpuredi.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mvcpuredi.User
import com.example.mvcpuredi.usecases.FetchUsersUseCase
import kotlinx.coroutines.*

class UserListFragment : BaseFragment(), UserListViewMvc.Listener {
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private var isDataLoaded = false
    private lateinit var viewMvc: UserListViewMvc

    lateinit var viewMvcFactory: ViewMvcFactory
    lateinit var fetchUsersUseCase: FetchUsersUseCase
    lateinit var dialogsNavigator: DialogsNavigator
    lateinit var screensNavigator: ScreensNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        viewMvc = viewMvcFactory.newUserListViewMvc(container)
        return viewMvc.rootView
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
        screensNavigator.toUserDetail(clickedUser.id.toString())
    }
}