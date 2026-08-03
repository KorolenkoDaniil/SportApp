package com.example.sportapp.support2026.features.user.authorisation


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportapp.support2026.features.user.authorisation.useCases.CheckAuthStatusUseCase
import com.example.sportapp.support2026.features.user.authorisation.useCases.LogInUseCase
import com.example.sportapp.support2026.features.user.authorisation.useCases.SignUpUseCase
import com.example.sportapp.support2026.features.user.authorisation.utils.AuthValidation
import com.example.sportapp.support2026.features.user.domain.entity.User
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


sealed class AuthState {
    data object Authenticated : AuthState()
    data object Unauthenticated : AuthState()
    data object Loading : AuthState()
    data class Error(val message: String) : AuthState()
}


class AuthViewModel (
    private val checkAuthStatusUseCase: CheckAuthStatusUseCase,
    private val logInUseCase: LogInUseCase,
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    private val _firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val _authState = MutableStateFlow<AuthState>(AuthState.Unauthenticated)

    val authState: StateFlow<AuthState> = _authState

    val email = mutableStateOf("")
    val password = mutableStateOf("")

    init {
        checkAuthStatus()
    }

    private fun checkAuthStatus() {
        viewModelScope.launch {

            _authState.value = checkAuthStatusUseCase.invoke(
                firebaseAuth = _firebaseAuth
            )
        }
    }


    fun login(email: String, password: String) {

        if (!AuthValidation.checkEmail(email)) {
            _authState.value = AuthState.Error("Email can't be empty")
            return
        }

        if (!AuthValidation.checkPassword(password)) {
            _authState.value = AuthState.Error("Password can't be empty")
            return
        }

        _authState.value = AuthState.Loading

        viewModelScope.launch {
            val fbLoginResult = logInUseCase.fireBaseLogin(_firebaseAuth, email, password)

            if (fbLoginResult.isSuccess) {
                val serverResult:  User? = logInUseCase.serverLogin(email)

                if (serverResult != null){
                    _authState.value = AuthState.Authenticated
                }
                else {
                    _authState.value = AuthState.Error("server Login failed")
                }
            }
            else {
                _authState.value = AuthState.Error("firebase Login failed")
            }
        }
    }


    fun signup(email: String, password: String) {

        if (!AuthValidation.checkEmail(email)) {
            _authState.value = AuthState.Error("Email can't be empty")
            return
        }

        if (!AuthValidation.checkPassword(password)) {
            _authState.value = AuthState.Error("Password can't be empty")
            return
        }

        viewModelScope.launch {
            val fbLoginResult = signUpUseCase.fireBasSignUp(_firebaseAuth, email, password)

            if (fbLoginResult.isSuccess) {
                val serverResult:  User? = signUpUseCase.serverSignUp(email)

                if (serverResult != null){
                    _authState.value = AuthState.Authenticated
                }
                else {
                    _authState.value = AuthState.Error("server Login failed")
                }
            }
            else {
                _authState.value = AuthState.Error("firebase Login failed")
            }
        }



    }








//    fun deleteUser(navController: NavHostController) {
//        viewModelScope.launch {
//            _authState.value = try {
//                auth.currentUser?.delete()?.await()
//                signOut(navController)
//                AuthState.Unauthenticated
//            } catch (e: Exception) {
//                AuthState.Error(e.message ?: "Failed to delete user")
//            }
//        }
//    }


//    fun signOut(navController: NavHostController) {
//        auth.signOut()
//        _authState.value = AuthState.Unauthenticated
//        navController.navigate(Screen.LoginPage.route)
//        authorizationUtils.changeImageState(ImageSubmissionState.Initial, _imageState)
//    }
//




}


