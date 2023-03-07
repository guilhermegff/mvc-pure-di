package com.example.mvcpuredi.di.presentation

import android.view.LayoutInflater
import androidx.fragment.app.FragmentManager
import com.example.mvcpuredi.screens.common.DialogsNavigator
import com.example.mvcpuredi.screens.common.ViewMvcFactory
import dagger.Module
import dagger.Provides

@Module
class PresentationModule() {
    @Provides
    fun viewMvcFactory(layoutInflater: LayoutInflater) = ViewMvcFactory(layoutInflater)

    @Provides
    fun dialogsNavigator(fragmentManager: FragmentManager) = DialogsNavigator(fragmentManager)
}