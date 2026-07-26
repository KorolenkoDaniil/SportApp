package com.example.sportapp.support2026.features.user.data.dto.requests

import com.example.sportapp.support2026.features.user.domain.entity.User

object UserRequestMapper {

    fun MapEntityToDto(entity: User): UserRequestDto {
        return UserRequestDto(
            userName = entity.userName,
            email = entity.email
        )
    }
}