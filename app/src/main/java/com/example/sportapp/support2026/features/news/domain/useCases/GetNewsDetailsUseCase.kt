package com.example.sportapp.support2026.features.news.domain.useCases

import com.example.sportapp.support2026.features.news.domain.entities.news.NewsDetails
import com.example.sportapp.support2026.features.news.domain.repository.NewsDomainRepository
import javax.inject.Inject

class GetNewsDetailsUseCase @Inject constructor(
    private val newsRepository: NewsDomainRepository
) {
    suspend operator fun invoke(newsId: Int): NewsDetails {

        return newsRepository.getNewsDetails(newsId)
    }
}