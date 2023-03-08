package com.example.mvcpuredi.screens

import androidx.lifecycle.*
import com.example.mvcpuredi.User
import com.example.mvcpuredi.usecases.FetchUserDetailUseCase
import com.example.mvcpuredi.usecases.FetchUsersUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

class MyViewModel @Inject constructor(
    private val fetchUsersUseCase: FetchUsersUseCase,
    private val fetchUserDetailUseCase: FetchUserDetailUseCase
) : ViewModel() {
    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users

    init {
        viewModelScope.launch {
            val result = fetchUsersUseCase.fetchUsers()
            if (result is FetchUsersUseCase.Result.Success) {
                _users.value = result.users
            } else {
                throw java.lang.RuntimeException("Fetch failed")
            }
        }
    }

    class Factory @Inject constructor(
        private val myViewModelProvider: Provider<MyViewModel>,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return myViewModelProvider.get() as T
        }
    }
}