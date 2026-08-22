package com.example.sportapp.support2026.features.user.authorisation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportapp.support2026.features.user.authorisation.useCases.CheckAuthStatusUseCase
import com.example.sportapp.support2026.features.user.authorisation.useCases.LogInUseCase
import com.example.sportapp.support2026.features.user.authorisation.useCases.SignUpUseCase
import com.example.sportapp.support2026.features.user.authorisation.utils.AuthValidation
import com.example.sportapp.support2026.features.user.domain.entity.User
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class AuthState {
    data class Authenticated(val email: String) : AuthState()
    data object Unauthenticated : AuthState()
    data object Loading : AuthState()
    data class Error(val message: String) : AuthState()
}

@HiltViewModel
class AuthViewModel @Inject constructor (
    private val checkAuthStatusUseCase: CheckAuthStatusUseCase,
    private val logInUseCase: LogInUseCase,
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    private val _firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val _authState = MutableStateFlow<AuthState>(AuthState.Unauthenticated)

    val authState: StateFlow<AuthState> = _authState.asStateFlow()

//    val email = mutableStateOf("")
//    val password = mutableStateOf("")

    init {
        checkAuthStatus()
    }

    private fun checkAuthStatus() {
        viewModelScope.launch {
            // Вызываем UseCase — он вернет AuthState.Authenticated(email), Unauthenticated или Error
            _authState.value = checkAuthStatusUseCase(_firebaseAuth)
            Log.d("checkAuthStatus", "${_authState.value}")
        }
    }

    fun login(emailInput: String, passwordInput: String) {
        if (!validateInput(emailInput, passwordInput)) return

        _authState.value = AuthState.Loading

        viewModelScope.launch {
            val fbLoginResult = logInUseCase.fireBaseLogin(_firebaseAuth, emailInput, passwordInput)

            if (fbLoginResult.isSuccess) {
                val serverResult: Boolean = logInUseCase.serverLogin(emailInput)

                if (serverResult) {
                    _authState.value = AuthState.Authenticated(emailInput)
                } else {
                    _authState.value = AuthState.Error("Server Login failed")
                }
            } else {
                _authState.value = AuthState.Error("Firebase Login failed")
            }
        }
    }

    fun signup(emailInput: String, passwordInput: String) {
        if (!validateInput(emailInput, passwordInput)) return

        _authState.value = AuthState.Loading

        viewModelScope.launch {
            val fbLoginResult = signUpUseCase.fireBasSignUp(_firebaseAuth, emailInput, passwordInput)

            if (fbLoginResult.isSuccess) {
                val serverResult: User? = signUpUseCase.serverSignUp(emailInput)

                if (serverResult != null) {
                    _authState.value = AuthState.Authenticated(emailInput)
                } else {
                    _authState.value = AuthState.Error("Server SignUp failed")
                }
            } else {
                _authState.value = AuthState.Error("Firebase SignUp failed")
            }
        }
    }

    private fun validateInput(emailInput: String, passwordInput: String): Boolean {
        if (!AuthValidation.checkEmail(emailInput)) {
            _authState.value = AuthState.Error("Email can't be empty")
            return false
        }
        if (!AuthValidation.checkPassword(passwordInput)) {
            _authState.value = AuthState.Error("Password can't be empty")
            return false
        }
        return true
    }
}