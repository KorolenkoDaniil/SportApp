package com.example.sportapp.support2026.features.news.data.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient

@Module
@InstallIn(SingletonComponent::class)
object NewsApiModule {

    @Provides
    fun provideNewsNetworkProvider(client: HttpClient): NewsNetworkProvider {
        return NewsNetworkProvider(client)
    }

    @Provides
    fun provideNewsApi(
        provider: NewsNetworkProvider
    ): NewsApi {
        return NewsApiImpl(provider)
    }
}