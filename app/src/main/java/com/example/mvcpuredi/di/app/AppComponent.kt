package com.example.mvcpuredi.di.app

import android.app.Application
import com.example.mvcpuredi.networking.UsersApi
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    fun application(): Application
    fun usersApi(): UsersApi
}