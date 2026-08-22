package com.example.sportapp.support2026.features.user.data.repository

import android.util.Log
import com.example.sportapp.support2026.features.user.data.api.UserApi
import com.example.sportapp.support2026.features.user.data.dto.requests.CreateUserRequestDto
import com.example.sportapp.support2026.features.user.data.dto.requests.UserRequestDto
import com.example.sportapp.support2026.features.user.data.dto.response.UserResponseMapper
import com.example.sportapp.support2026.features.user.domain.entity.User
import com.example.sportapp.support2026.features.user.domain.repository.UserDomainRepository
import javax.inject.Inject

class UserDataRepository @Inject constructor(
    private val api: UserApi
) : UserDomainRepository {

    override suspend fun addUser(email: String) : User {

        val requestDto = CreateUserRequestDto(
            email = email
        )

        Log.d("signup","requestDto $requestDto")

        val responseDto = api.addUser(
            userRequestDto = requestDto
        )
        Log.d("signup", "responseDto $responseDto")

        return UserResponseMapper.mapDtoToEntity(responseDto)
    }


    override suspend fun getUser(
        email: String,
    ): User {

        val dto = UserRequestDto(
            email = email
        )

        val responseDto = api.getUser(dto)

        val user = UserResponseMapper.mapDtoToEntity(responseDto)
        return user
    }

    override suspend fun checkUser(email: String): Boolean {

        val result = api.checkUserOnServer(email)

        return result
    }
}