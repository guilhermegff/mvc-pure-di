package com.example.mvcpuredi.screens.common

import androidx.fragment.app.FragmentManager
import javax.inject.Inject

class DialogsNavigator @Inject constructor(private val fragmentManager: FragmentManager) {
    fun showServerErrorDialog() {
        fragmentManager.beginTransaction().add(ServerErrorDialogFragment.newInstance(), null)
            .commitAllowingStateLoss()
    }
}