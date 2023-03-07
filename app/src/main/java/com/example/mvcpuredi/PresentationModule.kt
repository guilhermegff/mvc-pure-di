package com.example.mvcpuredi

import android.view.LayoutInflater
import androidx.fragment.app.FragmentManager
import com.example.mvcpuredi.networking.UsersApi
import com.example.mvcpuredi.screens.DialogsNavigator
import com.example.mvcpuredi.screens.ViewMvcFactory
import com.example.mvcpuredi.usecases.FetchUserDetailUseCase
import com.example.mvcpuredi.usecases.FetchUsersUseCase
import dagger.Module
import dagger.Provides

@Module
class PresentationModule(private val activityCompositionRoot: ActivityCompositionRoot) {
    @Provides
    fun layoutInflater() = activityCompositionRoot.layoutInflater

    @Provides
    fun fragmentManager() = activityCompositionRoot.fragmentManager

    @Provides
    fun usersApi() = activityCompositionRoot.usersApi

    @Provides
    fun activity() = activityCompositionRoot.activity

    @Provides
    fun screenNavigator() = activityCompositionRoot.screensNavigator

    @Provides
    fun viewMvcFactory(layoutInflater: LayoutInflater) = ViewMvcFactory(layoutInflater)

    @Provides
    fun dialogsNavigator(fragmentManager: FragmentManager) = DialogsNavigator(fragmentManager)

    @Provides
    fun fetchUsersUseCase(usersApi: UsersApi) = FetchUsersUseCase(usersApi)

    @Provides
    fun fetchUserDetailUseCase(usersApi: UsersApi) = FetchUserDetailUseCase(usersApi)
}