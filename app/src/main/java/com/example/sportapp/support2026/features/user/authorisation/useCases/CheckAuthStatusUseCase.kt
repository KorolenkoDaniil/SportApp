package com.example.sportapp.support2026.features.user.authorisation.useCases

import com.example.sportapp.support2026.features.user.authorisation.AuthState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CheckAuthStatusUseCase @Inject constructor() {

    suspend operator fun invoke(firebaseAuth: FirebaseAuth): AuthState {
        return try {
            val user = firebaseAuth.currentUser ?:
                return AuthState.Unauthenticated

            user.reload().await()

            if (firebaseAuth.currentUser != null) {
                AuthState.Authenticated
            } else {
                AuthState.Unauthenticated
            }
        } catch (e: Exception) {
            AuthState.Error(e.message ?: "Failed to check auth status")
        }
    }
}