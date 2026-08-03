package com.example.sportapp.support2026.features.user.domain.useCases

import com.example.sportapp.support2026.features.user.domain.entity.User
import com.example.sportapp.support2026.features.user.domain.repository.UserDomainRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: UserDomainRepository
) {

    suspend fun invoke (email: String): User{
        return repository.getUser(email)
    }
}