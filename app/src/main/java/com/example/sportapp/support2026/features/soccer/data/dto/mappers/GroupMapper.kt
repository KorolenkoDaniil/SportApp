package com.example.sportapp.support2026.features.soccer.data.dto.mappers

import com.example.sportapp.support2026.features.soccer.data.dto.GroupDto
import com.example.sportapp.support2026.features.soccer.domain.entities.Group

fun GroupDto.toDomain(): Group {
    return Group(
        groupName = this.groupName.orEmpty(),
        groupOrderID = this.groupOrderID ?: 0,
        groupID = this.groupID ?: 0
    )
}

fun Group.toDto(): GroupDto {
    return GroupDto(
        groupName = this.groupName,
        groupOrderID = this.groupOrderID,
        groupID = this.groupID
    )
}