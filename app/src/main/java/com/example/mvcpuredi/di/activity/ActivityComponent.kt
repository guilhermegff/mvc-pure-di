package com.example.mvcpuredi.di.activity

import com.example.mvcpuredi.di.app.AppComponent
import com.example.mvcpuredi.di.presentation.PresentationComponent
import com.example.mvcpuredi.di.presentation.PresentationModule
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {
    fun newPresentationComponent(presentationModule: PresentationModule): PresentationComponent
}