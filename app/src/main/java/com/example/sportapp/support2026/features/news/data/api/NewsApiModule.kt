package com.example.sportapp.support2026.features.news.data.api

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NewsApiModule {


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
}