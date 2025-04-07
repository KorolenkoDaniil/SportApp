package com.example.sportapp.models.news

import com.example.sportapp.models.news.api.internalNewsModels.NewsTagResponse
import com.example.sportapp.models.news.domain.internalNewsModels.NewsTagItem

class TagsMapper {

    fun tagResponseToItems(tagsResponse: List<NewsTagResponse>): List<NewsTagItem> {
        return tagsResponse.map { response ->
            NewsTagItem(
                tagId = response.tagId,
                tag = response.tag,
                newsDateTime = response.newsDateTime
            )
        }
    }
}