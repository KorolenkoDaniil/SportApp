package com.example.sportapp.support2026.features.comments.data.api

import com.example.sportapp.support2026.features.comments.data.repository.CommentsDataRepository
import com.example.sportapp.support2026.features.comments.domain.repository.CommentsDomainRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CommentsModule {

    @Binds
    @Singleton
    abstract fun bindCommentsApi(
        commentsApiImpl: CommentsApiImpl
    ): CommentsApi

    @Binds
    @Singleton
    abstract fun bindCommentsRepository(
        commentsDataRepository: CommentsDataRepository
    ): CommentsDomainRepository
}