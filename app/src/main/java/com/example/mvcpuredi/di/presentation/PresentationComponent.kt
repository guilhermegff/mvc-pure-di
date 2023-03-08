package com.example.mvcpuredi.di.presentation

import com.example.mvcpuredi.screens.UserDetailActivity
import com.example.mvcpuredi.screens.UserListFragment
import com.example.mvcpuredi.screens.ViewModelActivity
import dagger.Subcomponent

@PresentationScope
@Subcomponent(
    //modules = [PresentationModule::class, UseCasesModule::class]
)
interface PresentationComponent {
    fun inject(fragment: UserListFragment)
    fun inject(activity: UserDetailActivity)
    fun inject(viewModelActivity: ViewModelActivity)
}