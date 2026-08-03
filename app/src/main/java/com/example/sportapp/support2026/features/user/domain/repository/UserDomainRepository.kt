package com.example.sportapp.support2026.features.user.domain.repository

import com.example.sportapp.support2026.features.user.domain.entity.User

interface UserDomainRepository {
    suspend fun  addUser(email: String): User
//    suspend fun getUser(token: String?, email: String?): User
    suspend fun getUser(email: String): User
}