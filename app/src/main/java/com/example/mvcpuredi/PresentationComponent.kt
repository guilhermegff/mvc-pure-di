package com.example.mvcpuredi

import com.example.mvcpuredi.screens.DialogsNavigator
import com.example.mvcpuredi.screens.ScreensNavigator
import com.example.mvcpuredi.screens.ViewMvcFactory
import com.example.mvcpuredi.usecases.FetchUserDetailUseCase
import com.example.mvcpuredi.usecases.FetchUsersUseCase
import dagger.Component

@Component(modules = [PresentationModule::class])
interface PresentationComponent {
    fun viewMvcFactory(): ViewMvcFactory
    fun screensNavigator(): ScreensNavigator
    fun dialogsNavigator(): DialogsNavigator
    fun fetchUsersUseCase(): FetchUsersUseCase
    fun fetchUserDetailUseCase(): FetchUserDetailUseCase
}