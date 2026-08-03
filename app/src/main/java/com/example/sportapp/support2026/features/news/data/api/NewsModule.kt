package com.example.sportapp.support2026.features.news.data.api

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


////это ручной способ
//    @Provides
//    fun provideNewsApi(
//        provider: NewsNetworkProvider
//    ): NewsApi {
//        return NewsApiImpl(provider)
//    }

    @Binds
    @Singleton
    abstract fun bindNewsApi(
        newsApiImpl: NewsApiImpl
    ): NewsApi

    @Binds
    @Singleton
    abstract fun bindNewsRepository(
        newsDataRepository: NewsDataRepository // Сюда передаем ваш класс реализации
    ): NewsDomainRepository // А здесь возвращаем доменный интерфейс
}