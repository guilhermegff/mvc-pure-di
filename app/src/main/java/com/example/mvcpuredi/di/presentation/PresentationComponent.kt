package com.example.mvcpuredi.di.presentation

import com.example.mvcpuredi.screens.common.DialogsNavigator
import com.example.mvcpuredi.screens.common.ScreensNavigator
import com.example.mvcpuredi.screens.common.ViewMvcFactory
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