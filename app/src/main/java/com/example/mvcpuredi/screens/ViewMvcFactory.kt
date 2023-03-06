package com.example.mvcpuredi.screens

import android.view.LayoutInflater
import android.view.ViewGroup

class ViewMvcFactory(private val layoutInflater: LayoutInflater) {
    fun newUserListViewMvc(parent: ViewGroup?): UserListViewMvc {
        return UserListViewMvc(layoutInflater, parent)
    }

    fun newUserDetailViewMvc(parent: ViewGroup?): UserDetailViewMvc {
        return UserDetailViewMvc(layoutInflater, parent)
    }
}