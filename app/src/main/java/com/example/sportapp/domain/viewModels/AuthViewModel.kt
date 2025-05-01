package com.example.sportapp.models.viewModels


import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.sportapp.CleanArchitexture.data.repositories.UserRepository
import com.example.sportapp.CleanArchitexture.domain.models.user.UserEntity
import com.example.sportapp.CleanArchitexture.domain.preferencesManager.PreferencesManager
import com.example.sportapp.presentation.navigation.Screen
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.io.File


class AuthViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val _authState = MutableStateFlow<AuthState>(AuthState.Loading)
    val authState: StateFlow<AuthState> = _authState
    private val _currentUser = MutableStateFlow<UserEntity?>(null)
    val currentUser: StateFlow<UserEntity?> = _currentUser
    private val userRep = UserRepository()

    init {
        checkAuthStatus()
    }

    private fun checkAuthStatus() {
        viewModelScope.launch {
            _authState.value = try {
                if (auth.currentUser == null) {
                    AuthState.Unauthenticated
                } else {
                    auth.currentUser?.reload()?.await()
                    if (auth.currentUser != null) {
                        updateCurrentUser(userRep.getUser(auth.currentUser!!.email!!))
                        AuthState.Authenticated
                    } else {
                        AuthState.Unauthenticated
                    }
                }
            } catch (e: Exception) {
                AuthState.Error(e.message ?: "Failed to check auth status")
            }
        }
    }

    fun login(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error("Email or password can't be empty")
            return
        }

        _authState.value = AuthState.Loading
        viewModelScope.launch {
            _authState.value = try {
                auth.signInWithEmailAndPassword(email, password).await()
                AuthState.Authenticated
            } catch (e: Exception) {
                AuthState.Error(e.message ?: "Login failed")
            }
        }
    }

    fun signup(email: String, password: String, context: Context) {
        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error("Email or password can't be empty")
            return
        }

        _authState.value = AuthState.Loading
        viewModelScope.launch {
            _authState.value = try {
                auth.createUserWithEmailAndPassword(email, password).await()
                val user = withContext(Dispatchers.IO) {
                    userRep.putNewUser(auth.currentUser?.email ?: "")
                }
                updateCurrentUser(user)
                Log.d("currentUser", "User created successfully: $user")
                val sharedPrefManager = PreferencesManager(context)
                sharedPrefManager.saveLimit(10)
                AuthState.Authenticated
            } catch (e: Exception) {
                AuthState.Error(e.message ?: "Signup failed")
            }
        }
    }

    fun deleteUser(navController: NavHostController) {
        viewModelScope.launch {
            _authState.value = try {
                auth.currentUser?.delete()?.await()
                signOut(navController)
                AuthState.Unauthenticated
            } catch (e: Exception) {
                AuthState.Error(e.message ?: "Failed to delete user")
            }
        }
    }

    fun signOut(navController: NavHostController) {
        auth.signOut()
        _authState.value = AuthState.Unauthenticated
        navController.navigate(Screen.LoginPage.route)
    }


    fun sendUserImage(image: File) {
        viewModelScope.launch {
            userRep.uploadImage(
                imageFile = image
            )
        }
    }

    private fun updateCurrentUser(user: UserEntity?) {
        _currentUser.value = user
    }

}

sealed class AuthState {
    data object Authenticated : AuthState()
    data object Unauthenticated : AuthState()
    data object Loading : AuthState()
    data class Error(val message: String) : AuthState()
}