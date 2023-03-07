package com.example.mvcpuredi

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.mvcpuredi.networking.UsersApi
import com.example.mvcpuredi.screens.ScreensNavigator
import dagger.Component

@Component(modules = [ActivityModule::class])
interface ActivityComponent {
    fun activity(): AppCompatActivity
    fun layoutInflater(): LayoutInflater
    fun fragmentManager(): FragmentManager
    fun usersApi(): UsersApi
    fun screensNavigator(): ScreensNavigator
}