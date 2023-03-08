package com.example.mvcpuredi.screens.common

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.mvcpuredi.screens.UserDetailViewMvc
import com.example.mvcpuredi.screens.UserListViewMvc
import javax.inject.Inject
import javax.inject.Provider

class ViewMvcFactory @Inject constructor(
    private val layoutInflaterProvider: Provider<LayoutInflater>,
) {
    fun newUserListViewMvc(parent: ViewGroup?): UserListViewMvc {
        return UserListViewMvc(layoutInflaterProvider.get(), parent)
    }

    fun newUserDetailViewMvc(parent: ViewGroup?): UserDetailViewMvc {
        return UserDetailViewMvc(layoutInflaterProvider.get(), parent)
    }
}