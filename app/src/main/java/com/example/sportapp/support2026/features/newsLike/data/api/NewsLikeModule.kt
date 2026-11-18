package com.example.sportapp.support2026.features.newsLike.data.api

import com.example.sportapp.support2026.features.newsLike.data.NewsLikeDataRepository
import com.example.sportapp.support2026.features.newsLike.domain.repository.NewsLikeDomainRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NewsLikeModule {

    @Binds
    @Singleton
    abstract fun bindNewsLikeApi(
        newsLikeImpl: NewsLikeImpl
    ): NewsLikeApi

    @Binds
    @Singleton
    abstract fun bindNewsLikeRepository(
        newsLikeDataRepository: NewsLikeDataRepository
    ): NewsLikeDomainRepository
}