package com.example.mvcpuredi

import android.app.Activity
import com.example.mvcpuredi.screens.ScreensNavigator
import com.example.mvcpuredi.usecases.FetchUserDetailUseCase
import com.example.mvcpuredi.usecases.FetchUsersUseCase

class ActivityCompositionRoot(
    private val activity: Activity,
    private val appCompositionRoot: AppCompositionRoot,
) {
    val screensNavigator by lazy {
        ScreensNavigator(activity)
    }

    private val usersApi get() = appCompositionRoot.usersApi

    val fetchUsersUseCase get() = FetchUsersUseCase(usersApi)

    val fetchUserDetailUseCase get() = FetchUserDetailUseCase(usersApi)
}