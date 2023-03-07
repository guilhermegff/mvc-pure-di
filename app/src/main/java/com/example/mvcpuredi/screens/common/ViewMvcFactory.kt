package com.example.mvcpuredi.screens.common

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.mvcpuredi.screens.UserDetailViewMvc
import com.example.mvcpuredi.screens.UserListViewMvc

class ViewMvcFactory(private val layoutInflater: LayoutInflater) {
    fun newUserListViewMvc(parent: ViewGroup?): UserListViewMvc {
        return UserListViewMvc(layoutInflater, parent)
    }

    fun newUserDetailViewMvc(parent: ViewGroup?): UserDetailViewMvc {
        return UserDetailViewMvc(layoutInflater, parent)
    }
}