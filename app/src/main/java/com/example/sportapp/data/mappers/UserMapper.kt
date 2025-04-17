package com.example.sportapp.CleanArchitexture.data.mappers

import com.example.sportapp.CleanArchitexture.data.dto.user.UserDto
import com.example.sportapp.CleanArchitexture.domain.models.user.UserEntity
import com.example.sportapp.data.BaseUrl

class UserMapper {
    fun UserResponseToEntity(response: UserDto): UserEntity {
        return UserEntity(
            email = response.email,
            pictureURL = BaseUrl + "/UserImage/" + response.pictureId,
        )
    }

    fun ConvertToDto(entity: UserEntity): UserDto {
        val pictureId = entity.pictureURL.substringAfterLast("/")

        return UserDto(
            email = entity.email,
            pictureId = pictureId
        )
    }

}

