package com.example.sportapp.models.viewModels

import com.example.sportapp.models.user.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface UserViewModelInterface <T> : BaseViewModelInterface<T> {
    override val state: MutableStateFlow<T>

    val userRepository: UserRepository

    override fun getState(): StateFlow<T>

    override fun loadData()
}