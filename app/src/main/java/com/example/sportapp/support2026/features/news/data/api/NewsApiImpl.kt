package com.example.sportapp.support2026.features.news.data.api

import com.example.sportapp.support2026.features.news.data.dto.NewsPageDto
import javax.inject.Inject

class NewsApiImpl @Inject constructor(
    private val networkProvider: NewsNetworkProvider
) : NewsApi {

    override suspend fun getPaginatedNewsList(pageNumber: Int): NewsPageDto {

        return networkProvider.getPaginatedNews(pageNumber)
    }
}
