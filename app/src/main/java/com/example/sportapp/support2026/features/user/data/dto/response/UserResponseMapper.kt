package com.example.sportapp.support2026.features.user.data.dto.response

import com.example.sportapp.support2026.features.user.domain.entity.User

object UserResponseMapper {

    fun MapDtoToEntity (dto: UserResponseDto): User {
        return User(
            id = dto.id,
            userName = dto.userName,
            email = dto.email,
            imageId = dto.imageId
        )
    }
}