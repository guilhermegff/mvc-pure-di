package com.example.mvcpuredi.di.app

import com.example.mvcpuredi.di.activity.ActivityComponent
import com.example.mvcpuredi.di.activity.ActivityModule
import com.example.mvcpuredi.di.service.ServiceComponent
import com.example.mvcpuredi.di.service.ServiceModule
import dagger.Component

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {
    fun newActivityComponent(activityModule: ActivityModule): ActivityComponent
    fun newServiceComponent(serviceModule: ServiceModule): ServiceComponent
}