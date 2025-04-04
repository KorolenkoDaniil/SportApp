package com.example.sportapp.models.viewModels


import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.sportapp.models.preferencesManager.PreferencesManager
import com.example.sportapp.models.user.UserRepository
import com.example.sportapp.models.user.domain.UserEntity
import com.example.sportapp.pagesAndWidgets.pages.Screen
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


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
                        Log.d("currentUser", currentUser.value.toString())
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

    private fun updateCurrentUser(user: UserEntity?) {
        _currentUser.value = user
    }


//    fun createNonce(): String {
//        val rawNonce = UUID.randomUUID().toString()
//        val bytes = rawNonce.toByteArray()
//        val md = MessageDigest.getInstance("SHA-256")
//        val digest = md.digest(bytes)
//
//        return digest.fold("") { str, it ->
//            str + String.format("%02x", it) // Исправлено
//        }
//    }
//
////    Отлично, у вас есть функция createNonce, которая генерирует nonce (число, используемое один раз) для безопасности. Давайте разберем, что она делает, и обсудим некоторые моменты.
////
////    Разбор функции createNonce:
////
////    val rawNonce = UUID.randomUUID().toString():
////
////    Генерирует универсальный уникальный идентификатор (UUID) в виде строки. Это ваша "сырая" nonce. UUIDs гарантируют высокую вероятность уникальности.
////    val bytes = rawNonce.toByteArray():
////
////    Преобразует строку UUID в массив байтов, так как алгоритмы хеширования работают с байтами.
////    val md = MessageDigest.getInstance("SHA-256"):
////
////    Получает экземпляр MessageDigest, который реализует алгоритм хеширования SHA-256. SHA-256 — это криптографически безопасный алгоритм хеширования, который генерирует 256-битный (32-байтовый) хеш.
////    val digest = md.digest(bytes):
////
////    Вычисляет хеш SHA-256 массива байтов, полученного из UUID. digest — это массив байтов, представляющий хеш.
////    return digest.fold("") { str, it -> str + "%02x".format(it) }:
////
////    Преобразует массив байтов хеша в шестнадцатеричную строку.
////    fold("") начинает с пустой строки ("").
////    Для каждого байта (it) в массиве digest:
////    "%02x".format(it) форматирует байт в виде двухзначного шестнадцатеричного числа (например, 0A, FF).
////    str + ... добавляет отформатированный шестнадцатеричный байт к строке str.
//
//
//    fun signInWithGoogle(context: Context): Flow<AuthState> = callbackFlow {
//
//        val googleIdOption = GetGoogleIdOption.Builder()
//            .setFilterByAuthorizedAccounts(false)
//            .setServerClientId(context.getString(R.string.web_client_id))
//            .setAutoSelectEnabled(false)
//            .setNonce(createNonce())
//            .build()
//
//        val request = GetCredentialRequest.Builder()
//            .addCredentialOption(googleIdOption)
//            .build()
//    }
}

sealed class AuthState {
    data object Authenticated : AuthState()
    data object Unauthenticated : AuthState()
    data object Loading : AuthState()
    data class Error(val message: String) : AuthState()
}