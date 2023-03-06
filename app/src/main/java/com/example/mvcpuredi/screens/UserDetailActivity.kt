package com.example.mvcpuredi.screens

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.mvcpuredi.usecases.FetchUserDetailUseCase
import kotlinx.coroutines.*

class UserDetailActivity : AppCompatActivity(), UserDetailViewMvc.Listener {
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private lateinit var userId: String
    private lateinit var viewMvc: UserDetailViewMvc
    private lateinit var fetchUserDetailUseCase: FetchUserDetailUseCase
    private lateinit var dialogsNavigator: DialogsNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewMvc = UserDetailViewMvc(LayoutInflater.from(this), null)
        setContentView(viewMvc.rootView)
        fetchUserDetailUseCase = FetchUserDetailUseCase()

        dialogsNavigator = DialogsNavigator(supportFragmentManager)

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
                val result = fetchUserDetailUseCase.fetchUsers(userId.toInt())
                when (result) {
                    is FetchUserDetailUseCase.Result.Success -> {
                        viewMvc.bindUser(result.userId.toString())
                    }
                    is FetchUserDetailUseCase.Result.Failure -> onFetchFailed()
                }
            } finally {
                viewMvc.hideProgressIndication()
            }
        }
    }

    private fun onFetchFailed() {
        dialogsNavigator.showServerErrorDialog()
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