package com.example.mvcpuredi.di.presentation

import com.example.mvcpuredi.screens.UserDetailActivity
import com.example.mvcpuredi.screens.UserListFragment
import com.example.mvcpuredi.screens.ViewModelActivity
import dagger.Subcomponent

@PresentationScope
@Subcomponent(
    modules = [ViewModelsModule::class]
)
interface PresentationComponent {
    fun inject(fragment: UserListFragment)
    fun inject(activity: UserDetailActivity)
    fun inject(viewModelActivity: ViewModelActivity)
}