package com.example.mvcpuredi.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.mvcpuredi.R
import com.example.mvcpuredi.User
import com.example.mvcpuredi.networking.UserResponse
import com.example.mvcpuredi.networking.UsersApi
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserListActivity : AppCompatActivity() {
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var usersAdapter: UsersAdapter
    private lateinit var usersApi: UsersApi

    private var isDataLoaded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_list)

        swipeRefreshLayout = findViewById(R.id.swipeRefresh)
        swipeRefreshLayout.setOnRefreshListener {
            fetchUsers()
        }

        recyclerView = findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        usersAdapter = UsersAdapter {
            UserDetailActivity.start(this, it.id.toString())
        }
        recyclerView.adapter = usersAdapter

        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient().newBuilder().addInterceptor(
                HttpLoggingInterceptor().apply {
                    this.level = HttpLoggingInterceptor.Level.BODY
                }
            ).build())
            .build()
        usersApi = retrofit.create(UsersApi::class.java)
    }

    override fun onStart() {
        super.onStart()
        if (!isDataLoaded) {
            fetchUsers()
        }
    }

    override fun onStop() {
        super.onStop()
        coroutineScope.coroutineContext.cancelChildren()
    }

    private fun fetchUsers() {
        coroutineScope.launch {
            showProgressIndication()
            try {
                val response = usersApi.getUsers()
                if (response.isSuccessful && response.body() != null) {
                    usersAdapter.bindData(
                        response.body()!!.map {
                            it.toUser()
                        }
                    )
                    isDataLoaded = true
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
        supportFragmentManager.beginTransaction()
            .add(ServerErrorDialogFragment.newInstance(), null)
            .commitAllowingStateLoss()
    }

    private fun showProgressIndication() {
        swipeRefreshLayout.isRefreshing = true
    }

    private fun hideProgressIndication() {
        if (swipeRefreshLayout.isRefreshing) {
            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun UserResponse.toUser() =
        User(
            id,
            name,
            status
        )

    class UsersAdapter(
        private val onUserClickListener: (User) -> Unit
    ) : RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {
        private var userList: List<User> = ArrayList(0)

        inner class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val name: TextView = view.findViewById(R.id.txt_name)
        }

        @SuppressLint("NotifyDataSetChanged")
        fun bindData(users: List<User>) {
            userList = ArrayList(users)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.user_list_item, parent, false)
            return UserViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
            holder.name.text = userList[position].name
            holder.itemView.setOnClickListener {
                onUserClickListener.invoke(userList[position])
            }
        }

        override fun getItemCount(): Int {
            return userList.size
        }
    }
}