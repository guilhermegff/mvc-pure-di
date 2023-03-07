package com.example.mvcpuredi.di.presentation

import com.example.mvcpuredi.di.activity.ActivityComponent
import com.example.mvcpuredi.screens.UserDetailActivity
import com.example.mvcpuredi.screens.UserListFragment
import dagger.Component

@PresentationScope
@Component(dependencies = [ActivityComponent::class], modules = [PresentationModule::class])
interface PresentationComponent {
    fun inject(fragment: UserListFragment)
    fun inject(activity: UserDetailActivity)
}