package com.example.sportapp.support2026.features.news.data.api

import com.example.sportapp.support2026.features.news.data.dto.newsDetails.NewsDetailsDto
import com.example.sportapp.support2026.features.news.data.dto.newsList.NewsPageDto

interface NewsApi {
    suspend fun getPaginatedNewsList(pageNumber: Int) : NewsPageDto

    suspend fun getNewsDetails(newsId: Int): NewsDetailsDto
}