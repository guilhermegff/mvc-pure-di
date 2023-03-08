package com.example.mvcpuredi.screens.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvcpuredi.screens.MyOtherViewModel
import com.example.mvcpuredi.screens.MyViewModel
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor(
    private val myViewModelProvider: Provider<MyViewModel>,
    private val myOtherViewModelProvider: Provider<MyOtherViewModel>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when(modelClass) {
            MyViewModel::class.java -> myViewModelProvider.get() as T
            MyOtherViewModel::class.java -> myOtherViewModelProvider.get() as T
            else -> throw RuntimeException("unsupported viewModel type: $modelClass")
        }
    }
}