package com.example.sportapp.support2026.features.user.domain.useCases

import android.util.Log
import com.example.sportapp.support2026.features.user.domain.entity.User
import com.example.sportapp.support2026.features.user.domain.repository.UserDomainRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: UserDomainRepository
) {

    suspend fun GetUserData(
        email: String,
    ): User? {
        return try {

            val user: User = repository.getUser(
                email = email
            )
            user
        } catch (e: Exception) {
            Log.d("exception", "${e.message}")
            null
        }
    }

}