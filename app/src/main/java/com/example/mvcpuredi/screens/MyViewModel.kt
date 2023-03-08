package com.example.mvcpuredi.screens

import androidx.lifecycle.*
import com.example.mvcpuredi.User
import com.example.mvcpuredi.usecases.FetchUsersUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

class MyViewModel @Inject constructor(
    private val fetchUsersUseCase: FetchUsersUseCase
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
        private val fetchUsersUseCase: Provider<FetchUsersUseCase>,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MyViewModel(fetchUsersUseCase.get()) as T
        }
    }
}