//package com.example.sportapp.models.user
//
//import com.google.firebase.Firebase
//import com.google.firebase.auth.auth
//import kotlinx.coroutines.flow.callbackFlow
//import kotlinx.coroutines.flow.Flow
//
//
//class AuthenticationManager {
//
//    private val auth = Firebase.auth
//
//    fun createAccountWithEmail(email: String, password: String): Flow<AuthResponse> = callbackFlow {
//        auth.createUserWithEmailAndPassword(email, password)
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    trySend(AuthResponse.Success).isSuccess
//                } else {
//                    trySend(AuthResponse.Error(task.exception?.message ?: "Ошибка регистрации")).isSuccess
//                }
//                close() // Закрываем поток после отправки данных
//            }
//        awaitClose() // Ждём завершения работы потока
//    }
//}
//
//sealed interface AuthResponse {
//    data object Success : AuthResponse
//    data class Error(val message: String) : AuthResponse
//}
//
