package com.example.mvcpuredi.di.presentation

import com.example.mvcpuredi.screens.UserDetailActivity
import com.example.mvcpuredi.screens.UserListFragment
import dagger.Component

@Component(modules = [PresentationModule::class])
interface PresentationComponent {
    fun inject(fragment: UserListFragment)
    fun inject(activity: UserDetailActivity)
}