package com.example.sportapp.support2026.features.user.authorisation.useCases

import com.example.sportapp.support2026.features.user.authorisation.AuthState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CheckAuthStatusUseCase @Inject constructor() {

    suspend operator fun invoke(firebaseAuth: FirebaseAuth): AuthState {
        return try {
            val user = firebaseAuth.currentUser ?: return AuthState.Unauthenticated

            // Обновляем данные пользователя из Firebase
            user.reload().await()

            val currentUser = firebaseAuth.currentUser
            val email = currentUser?.email

            if (currentUser != null && !email.isNullOrBlank()) {
                AuthState.Authenticated(email)
            } else {
                AuthState.Unauthenticated
            }
        } catch (e: Exception) {
            AuthState.Error(e.message ?: "Failed to check auth status")
        }
    }
}