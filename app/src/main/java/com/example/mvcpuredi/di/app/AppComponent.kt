package com.example.mvcpuredi.di.app

import com.example.mvcpuredi.di.activity.ActivityComponent
import com.example.mvcpuredi.di.activity.ActivityModule
import dagger.Component

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {
    fun newActivityComponent(activityModule: ActivityModule): ActivityComponent
}