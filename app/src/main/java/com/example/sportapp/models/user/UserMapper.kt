package com.example.sportapp.models.user

import com.example.sportapp.models.BaseUrl
import com.example.sportapp.models.user.api.UserItemResponse
import com.example.sportapp.models.user.domain.UserEntity

class UserMapper {
    fun UseResponseToEntity(response: UserItemResponse): UserEntity {
        return UserEntity(
            email = response.email,
            pictureURL = BaseUrl + "/UserImage/" + response.pictureId,
        )
    }
}

