package com.example.mvcpuredi

import com.example.mvcpuredi.screens.UserDetailActivity
import com.example.mvcpuredi.screens.UserListFragment

class Injector(private val compositionRoot: PresentationCompositionRoot) {
    fun inject(fragment: UserListFragment) {
        fragment.dialogsNavigator = compositionRoot.dialogsNavigator
        fragment.screensNavigator = compositionRoot.screensNavigator
        fragment.fetchUsersUseCase = compositionRoot.fetchUsersUseCase
        fragment.viewMvcFactory = compositionRoot.viewMvcFactory
    }

    fun inject(activity: UserDetailActivity) {
        activity.dialogsNavigator = compositionRoot.dialogsNavigator
        activity.screensNavigator = compositionRoot.screensNavigator
        activity.fetchUserDetailUseCase = compositionRoot.fetchUserDetailUseCase
        activity.viewMvcFactory = compositionRoot.viewMvcFactory
    }
}