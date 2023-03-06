package com.example.mvcpuredi

import com.example.mvcpuredi.screens.DialogsNavigator
import com.example.mvcpuredi.screens.ViewMvcFactory
import com.example.mvcpuredi.usecases.FetchUserDetailUseCase
import com.example.mvcpuredi.usecases.FetchUsersUseCase

class PresentationCompositionRoot(private val activityCompositionRoot: ActivityCompositionRoot) {
    private val layoutInflater get() = activityCompositionRoot.layoutInflater
    private val fragmentManager get() = activityCompositionRoot.fragmentManager
    private val usersApi get() = activityCompositionRoot.usersApi
    val screensNavigator get() = activityCompositionRoot.screensNavigator
    val viewMvcFactory get() = ViewMvcFactory(layoutInflater)
    val dialogsNavigator get() = DialogsNavigator(fragmentManager)
    val fetchUsersUseCase get() = FetchUsersUseCase(usersApi)
    val fetchUserDetailUseCase get() = FetchUserDetailUseCase(usersApi)
}