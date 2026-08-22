package com.example.sportapp.support2026.features.soccer.data.api;


import com.example.sportapp.support2026.features.soccer.data.repository.SoccerDataRepository
import com.example.sportapp.support2026.features.soccer.domain.repository.SoccerDomainRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SoccerModule {

    @Binds
    @Singleton
    abstract fun bindSoccerApi(
        soccerApiImplementation: SoccerApiImplementation
    ): SoccerApi

    @Binds
    @Singleton
    abstract fun bindSoccerRepository (
        soccerRepository: SoccerDataRepository
    ): SoccerDomainRepository

}
