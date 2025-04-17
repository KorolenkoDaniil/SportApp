package com.example.sportapp.CleanArchitexture.data.mappers

import com.example.sportapp.CleanArchitexture.data.dto.news.NewsTagDto
import com.example.sportapp.CleanArchitexture.domain.models.news.NewsTagEntity

class TagsMapper {

    fun tagResponseToItems(tagsResponse: List<NewsTagDto>): List<NewsTagEntity> {
        return tagsResponse.map { response ->
            NewsTagEntity(
                tag = response.tag,
                newsDateTime = response.newsDateTime
            )
        }
    }
}