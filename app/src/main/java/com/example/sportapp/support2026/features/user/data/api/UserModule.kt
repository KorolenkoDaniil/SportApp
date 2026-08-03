package com.example.sportapp.support2026.features.user.data.api

import com.example.sportapp.support2026.features.user.data.repository.UserDataRepository
import com.example.sportapp.support2026.features.user.domain.repository.UserDomainRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class UserModule {

    @Binds
    @Singleton
    abstract fun bindUserApi(
        userApiImpl: UserApiImpl
    ): UserApi

    @Binds
    @Singleton
    abstract fun bindUserRepository (
        userRepository: UserDataRepository
    ): UserDomainRepository
}