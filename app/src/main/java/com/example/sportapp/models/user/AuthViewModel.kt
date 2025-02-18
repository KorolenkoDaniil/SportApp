package com.example.sportapp.models.user

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.sportapp.Screen
import com.example.sportapp.models.user.domain.UserEntity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _authState: MutableStateFlow<AuthState> = MutableStateFlow(AuthState.Loading)

    val authState: StateFlow<AuthState> = _authState

    private val _currentUser = MutableStateFlow<UserEntity?>(null)
    val currentUser: StateFlow<UserEntity?> = _currentUser

    fun updateCurrentUser(user: UserEntity?) {
        _currentUser.value = user
    }

    private val userRep = UserRepository()


    init {
        checkAuthStatus()
    }

    private fun checkAuthStatus() {
        if (auth.currentUser == null) {
            _authState.value = AuthState.Unauthenticated
        } else {
            auth.currentUser?.reload()?.addOnCompleteListener { task ->
                if (task.isSuccessful && auth.currentUser != null) {
                    CoroutineScope(Dispatchers.IO).launch {
                        updateCurrentUser(async { userRep.getUser(auth.currentUser!!.email!!) }.await())
                        Log.d("currentUser", currentUser.toString())
                    }
                    _authState.value = AuthState.Authenticated

                } else {
                    _authState.value = AuthState.Unauthenticated
                }
            }
        }
    }

    fun login(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error("Email or password can't be empty")
            return
        }

        _authState.value = AuthState.Loading
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _authState.value = AuthState.Authenticated
                } else {
                    _authState.value =
                        AuthState.Error(task.exception?.message ?: "Something went wrong")
                }
            }
    }

    fun signup(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error("Email or password can't be empty")
            return
        }
        _authState.value = AuthState.Loading
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    CoroutineScope(Dispatchers.IO).launch {
                        try {
                            val user = async { userRep.putNewUser(auth.currentUser?.email ?: "") }.await()
                            updateCurrentUser(user)
                            Log.d("currentUser", "User created successfully: $user")
                        } catch (e: Exception) {
                            Log.e("currentUser", "Failed to create user", e)
                        }
                    }
                    _authState.value = AuthState.Authenticated
                } else {
                    _authState.value =
                        AuthState.Error(task.exception?.message ?: "Something went wrong")
                }
            }
    }

    fun deleteUser(navController: NavHostController) {
        val currentUser = auth.currentUser
        currentUser?.delete()?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                signOut(navController)
            } else {
                _authState.value =
                    AuthState.Error(task.exception?.message ?: "Failed to delete user")
            }
        }
    }

    fun signOut(navController: NavHostController) {
        auth.signOut()
        _authState.value = AuthState.Unauthenticated
        navController.navigate(Screen.LoginPage.route)
    }
}


sealed class AuthState {
    data object Authenticated : AuthState()
    data object Unauthenticated : AuthState()
    data object Loading : AuthState()
    data class Error(val message: String) : AuthState()
}