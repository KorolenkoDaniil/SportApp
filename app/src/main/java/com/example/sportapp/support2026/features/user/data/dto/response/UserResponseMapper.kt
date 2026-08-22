package com.example.sportapp.support2026.features.user.data.dto.response

import com.example.sportapp.support2026.features.user.domain.entity.User

object UserResponseMapper {

    private const val DEFAULT_IMAGE_URL = "https://img.magnific.com/free-photo/vertical-shot-wooden-passage-reflective-small-lake-mountain-range-horizon_181624-37099.jpg?semt=ais_hybrid&w=740&q=80"
    fun mapDtoToEntity (dto: UserResponseDto): User {
        return User(
            id = dto.id,
            userName = dto.userName,
            email = dto.email,
            imageId = dto.imageId?.takeIf { it.isNotBlank() } ?: DEFAULT_IMAGE_URL
        )
    }
}