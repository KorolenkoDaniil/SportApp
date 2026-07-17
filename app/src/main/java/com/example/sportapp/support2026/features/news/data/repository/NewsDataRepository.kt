package com.example.sportapp.support2026.features.news.data.repository


import com.example.sportapp.support2026.features.news.data.api.NewsApi
import com.example.sportapp.support2026.features.news.data.dto.newsDetails.NewsDetailsDto
import com.example.sportapp.support2026.features.news.data.dto.newsDetails.NewsDetailsMapper
import com.example.sportapp.support2026.features.news.data.dto.newsList.NewsPageDto
import com.example.sportapp.support2026.features.news.data.dto.newsList.mappers.NewsPageMapper
import com.example.sportapp.support2026.features.news.domain.entities.news.News
import com.example.sportapp.support2026.features.news.domain.entities.news.NewsDetails
import com.example.sportapp.support2026.features.news.domain.repository.NewsDomainRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsDataRepository @Inject constructor(
    private val api: NewsApi,
) : NewsDomainRepository {

    override suspend fun getNews(page: Int): List<News> {
        val dto : NewsPageDto = api.getPaginatedNewsList(page)

        val entityPage = NewsPageMapper.mapNewsPageToEntity(dto)
        return entityPage.itemsList
    }

    override suspend fun getNewsDetails(newsId: Int): NewsDetails {
        val dto : NewsDetailsDto = api.getNewsDetails(newsId)

        val newsDetailsEntity = NewsDetailsMapper.mapNewsDetailsDtoToEntity(dto)
        return newsDetailsEntity
    }
}


