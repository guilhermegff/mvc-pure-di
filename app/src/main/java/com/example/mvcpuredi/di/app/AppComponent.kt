package com.example.mvcpuredi.di.app

import com.example.mvcpuredi.di.activity.ActivityComponent
import com.example.mvcpuredi.di.service.ServiceComponent
import com.example.mvcpuredi.di.service.ServiceModule
import dagger.Component

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {
    fun newActivityComponentBuilder(): ActivityComponent.Builder
    fun newServiceComponent(serviceModule: ServiceModule): ServiceComponent
}