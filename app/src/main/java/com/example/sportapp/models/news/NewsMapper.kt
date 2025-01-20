package com.example.sportapp.models.news

import com.example.sportapp.models.news.api.NewsItemResponse
import com.example.sportapp.models.news.api.NewsResponse
import com.example.sportapp.models.news.domain.NewsEntity
import com.example.sportapp.models.news.domain.NewsListEntity

class NewsMapper {

    fun getNewsEntityList(response: NewsResponse, newsBaseUrl: String): NewsListEntity {
        return NewsListEntity(
            pageNumber = response.pageNumber,
            pageSize = response.pageSize,
            totalItems = response.totalItems,
            totalPages = response.totalPages,
            news = response.news.map { item ->
                NewsEntity(
                    dateTime = item.dateTime,
                    sport = item.sport,
                    title = item.title,
                    imageId = item.imageId,
                    newsImage = newsBaseUrl + "/images/" + item.imageId,
                    articleText = item.articleText
                )
            }
        )
    }

    fun getOneNewsEntity(response: NewsItemResponse, newsBaseUrl: String): NewsEntity {
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