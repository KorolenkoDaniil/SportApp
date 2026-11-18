package com.example.sportapp.support2026.features.news.data.di // или ваш актуальный пакет для модуля новостей

import com.example.sportapp.support2026.features.news.data.api.NewsApi
import com.example.sportapp.support2026.features.news.data.api.NewsApiImpl
import com.example.sportapp.support2026.features.news.data.repository.NewsDataRepository
import com.example.sportapp.support2026.features.news.domain.repository.NewsDomainRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NewsModule {

    @Binds
    @Singleton
    abstract fun bindNewsApi(
        newsApiImpl: NewsApiImpl
    ): NewsApi

    @Binds
    @Singleton
    abstract fun bindNewsRepository(
        newsDataRepository: NewsDataRepository
    ): NewsDomainRepository
}