package com.example.sportapp.support2026.features.soccer.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportapp.support2026.features.soccer.domain.entities.Match
import com.example.sportapp.support2026.features.soccer.domain.useCases.GetMatchByIdUseCase
import com.example.sportapp.support2026.features.soccer.domain.useCases.LoadLeagueMatchesUseCase
import com.example.sportapp.support2026.features.user.domain.entity.User
import com.example.sportapp.support2026.features.user.domain.useCases.GetUserUseCase
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SoccerViewModel @Inject constructor(
    private val loadLeagueMatchesUseCase: LoadLeagueMatchesUseCase,
    private val getMatchByIdUseCase: GetMatchByIdUseCase
) : ViewModel() {

    private val _matchesList: MutableStateFlow<List<Match>?> = MutableStateFlow(null)
    val matchesList: StateFlow<List<Match>?> = _matchesList.asStateFlow()


    init {
        viewModelScope.launch {
            loadMatches()
        }
    }

    suspend fun loadMatches () {
        _matchesList.value = loadLeagueMatchesUseCase.LoadLeagueMatches()
    }
}




@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val firebaseAuth: FirebaseAuth // 👈 Добавляем FirebaseAuth
) : ViewModel() {

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser.asStateFlow()

    init {
        viewModelScope.launch {
            checkAndLoadCurrentUser()
        }
    }

    suspend fun checkAndLoadCurrentUser() {
        val firebaseUser = firebaseAuth.currentUser
        val email = firebaseUser?.email

        if (!email.isNullOrBlank()) {
            loadUser(email)
        }
    }

    suspend fun loadUser(email: String) {

        Log.d("UserViewModel", "Отправляем запрос на сервер для email: $email")
        val user = getUserUseCase.GetUserData(email)
        _currentUser.value = user
        Log.d("UserViewModel", "Данные пользователя с сервера: $user")
    }
}