package com.example.sportapp.models.news

import com.example.sportapp.models.news.api.NewsResponse
import com.example.sportapp.models.news.domain.NewsEntity

class NewsMapper {

    fun getNewsEntityList (response: List<NewsResponse>): List<NewsEntity> {

        val newsBaseUrl = "https://d9b9-51-159-14-212.ngrok-free.app"

        return response.map { item ->

            NewsEntity(
                dateTime = item.dateTime,
                sport = item.sport,
                title = item.title,
                imageId = item.imageId,
                newsImage = newsBaseUrl + "/images/" + item.imageId
            )
        }
    }
}