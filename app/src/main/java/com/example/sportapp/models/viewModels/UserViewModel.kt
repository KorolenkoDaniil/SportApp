package com.example.sportapp.models.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportapp.models.user.UserRepository
import com.example.sportapp.models.user.domain.UserEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class UserViewModel: ViewModel(), UserViewModelInterface<UserState> {
    override val state: MutableStateFlow<UserState> = MutableStateFlow(UserState.Load)

    override val userRepository: UserRepository = UserRepository()

    override fun getState(): StateFlow<UserState> {
        return state
    }

    init {
        loadData()
    }

    override fun loadData() {}


    fun loadUser(email: String) {
        UserState.Load.also { state.value = it }
        viewModelScope.launch {
            try {
                val user = userRepository.getUser(email)
                state.value = UserState.UserContent(user)
            } catch (e: Throwable) {
                state.value = UserState.Error(e)
            }
        }
    }
}

sealed interface UserState : BaseState {
    data object Load : UserState
    data class Error(val e: Throwable) : UserState
    data class UserContent(val user: UserEntity) : UserState
}
