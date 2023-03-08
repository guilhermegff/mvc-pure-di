package com.example.mvcpuredi.di.presentation

import androidx.lifecycle.ViewModel
import com.example.mvcpuredi.di.ViewModelKey
import com.example.mvcpuredi.screens.MyOtherViewModel
import com.example.mvcpuredi.screens.MyViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(MyViewModel::class)
    abstract fun myViewModel(myViewModel: MyViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MyOtherViewModel::class)
    abstract fun myOtherViewModel(myOtherViewModel: MyOtherViewModel): ViewModel
}