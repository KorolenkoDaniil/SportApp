package com.example.sportapp.models.news

import com.example.sportapp.models.news.api.NewsResponse
import com.example.sportapp.models.news.domain.NewsEntity

class NewsMapper {

    fun getNewsEntityList(response: List<NewsResponse>, newsBaseUrl: String): List<NewsEntity> {
        return response.map { item ->

            NewsEntity(
                dateTime = item.dateTime,
                sport = item.sport,
                title = item.title,
                imageId = item.imageId,
                newsImage = newsBaseUrl + "/images/" + item.imageId,
                articleText = item.articleText
            )
        }
    }


    fun getOneNewsEntity(response: NewsResponse, newsBaseUrl: String): NewsEntity {
        return NewsEntity(
            dateTime = response.dateTime,
            sport = response.sport,
            title = response.title,
            imageId = response.imageId,
            newsImage = newsBaseUrl + "/images/" + response.imageId,
            articleText = response.articleText
        )
    }
}