package com.example.sportapp.support2026.features.user.data.api

import com.example.sportapp.support2026.features.user.data.dto.requests.CreateUserRequestDto
import com.example.sportapp.support2026.features.user.data.dto.requests.UserRequestDto
import com.example.sportapp.support2026.features.user.data.dto.response.UserResponseDto

interface UserApi {
    suspend fun addUser(userRequestDto: CreateUserRequestDto): UserResponseDto
    suspend fun getUser(dto: UserRequestDto) : UserResponseDto
    suspend fun checkUserOnServer (email: String) : Boolean
}