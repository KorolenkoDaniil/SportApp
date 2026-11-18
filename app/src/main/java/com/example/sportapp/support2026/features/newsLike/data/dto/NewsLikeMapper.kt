package com.example.sportapp.support2026.features.newsLike.data.dto

import com.example.sportapp.support2026.features.newsLike.domain.entities.NewsLike

class NewsLikeMapper {

    fun mapNewsLikeToEntity (dto: NewsLikeDto) : NewsLike {
        return NewsLike(
            newsId = dto.newsId,
            email = dto.email
        )
    }


    fun mapNewsLikeToDto (entity: NewsLike) : NewsLikeDto{
        return NewsLikeDto(
            newsId = entity.newsId,
            email = entity.email
        )
    }
}