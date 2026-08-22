package com.example.sportapp.support2026.features.user.authorisation.useCases

import android.util.Log
import com.example.sportapp.support2026.features.user.domain.repository.UserDomainRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class LogInUseCase @Inject constructor(
    private val repository: UserDomainRepository
) {
    suspend fun fireBaseLogin(
        auth: FirebaseAuth,
        email: String,
        password: String,
    ): Result<Unit> {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun serverLogin(
        email: String,
    ): Boolean {
        return try {
            val result: Boolean = repository.checkUser (
                email = email
            )
            result
        } catch (e: Exception) {
            Log.d("exception", "${e.message}")
            false
        }
    }
}