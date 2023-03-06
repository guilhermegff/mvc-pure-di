package com.example.mvcpuredi.screens

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.mvcpuredi.R
import com.example.mvcpuredi.User

class UserListViewMvc(
    private val layoutInflater: LayoutInflater,
    private val parent: ViewGroup?,
) {
    interface Listener {
        fun onRefreshClicked()
        fun onUserClicked(clickedUser: User)
    }

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var usersAdapter: UsersAdapter

    val rootView: View = layoutInflater.inflate(R.layout.user_list, parent, false)

    private val context: Context get() = rootView.context

    private val listeners = HashSet<Listener>()

    init {
        swipeRefreshLayout = findViewById(R.id.swipeRefresh)
        swipeRefreshLayout.setOnRefreshListener {
            for (listener in listeners) {
                listener.onRefreshClicked()
            }
        }

        recyclerView = findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(context)
        usersAdapter = UsersAdapter {
            for (listener in listeners) {
                listener.onUserClicked(it)
            }
        }
        recyclerView.adapter = usersAdapter
    }

    fun bindUsers(users: List<User>) {
        usersAdapter.bindData(users)
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
            val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.user_list_item, parent, false)
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