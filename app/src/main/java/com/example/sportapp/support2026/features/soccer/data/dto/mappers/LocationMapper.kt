package com.example.sportapp.support2026.features.soccer.data.dto.mappers

import com.example.sportapp.support2026.features.soccer.data.dto.LocationDto
import com.example.sportapp.support2026.features.soccer.domain.entities.Location

fun LocationDto.toDomain(): Location {
    return Location(
        locationID = this.locationID ?: 0,
        locationCity = this.locationCity.orEmpty(),
        locationStadium = this.locationStadium.orEmpty()
    )
}


fun Location.toDto(): LocationDto {
    return LocationDto(
        locationID = this.locationID,
        locationCity = this.locationCity,
        locationStadium = this.locationStadium
    )
}