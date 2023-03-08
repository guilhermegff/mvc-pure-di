package com.example.mvcpuredi.di.presentation

import android.view.LayoutInflater
import androidx.fragment.app.FragmentManager
import com.example.mvcpuredi.screens.common.DialogsNavigator
import com.example.mvcpuredi.screens.common.ViewMvcFactory
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
class PresentationModule() {
    @Provides
    fun viewMvcFactory(layoutInflaterProvider: Provider<LayoutInflater>) =
        ViewMvcFactory(layoutInflaterProvider)

    @Provides
    fun dialogsNavigator(fragmentManager: FragmentManager) = DialogsNavigator(fragmentManager)
}
