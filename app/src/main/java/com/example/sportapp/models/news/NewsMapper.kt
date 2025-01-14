package com.example.sportapp.models.news

import com.example.sportapp.models.news.api.NewsResponse
import com.example.sportapp.models.news.domain.NewsEntity

class NewsMapper {

    fun getNewsEntityList (response: List<NewsResponse>): List<NewsEntity> {

        return response.map { item ->
            NewsEntity(
                dateTime = item.dateTime,
                sport = item.sport,
                text = item.text
            )
        }
    }
}