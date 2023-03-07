package com.example.mvcpuredi.di.presentation

import com.example.mvcpuredi.networking.UsersApi
import com.example.mvcpuredi.usecases.FetchUserDetailUseCase
import com.example.mvcpuredi.usecases.FetchUsersUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {
    @Provides
    fun fetchUsersUseCase(usersApi: UsersApi) = FetchUsersUseCase(usersApi)

    @Provides
    fun fetchUserDetailUseCase(usersApi: UsersApi) = FetchUserDetailUseCase(usersApi)
}