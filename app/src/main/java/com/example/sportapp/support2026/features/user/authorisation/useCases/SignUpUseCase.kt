package com.example.sportapp.support2026.features.user.authorisation.useCases

import android.util.Log
import com.example.sportapp.support2026.features.user.domain.entity.User
import com.example.sportapp.support2026.features.user.domain.repository.UserDomainRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val repository: UserDomainRepository
) {
    suspend fun fireBasSignUp(
        auth: FirebaseAuth,
        email: String,
        password: String,
    ): Result<Unit> {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    suspend fun serverSignUp(
        email: String,
    ): User? {
        try {
            val user: User = repository.addUser(
                email = email
            )

            return user
        } catch (e: Exception) {
            Log.d("exception", "${e.message}")
            return null
        }
    }
}