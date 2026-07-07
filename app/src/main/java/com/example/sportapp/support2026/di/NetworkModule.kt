package com.example.sportapp.support2026.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

////Аннотация @InstallIn
////Указывает, в какой scope (жизненный цикл) будет жить зависимость.
////SingletonComponent → объект создаётся один раз на всё приложение.
////ActivityComponent → новый объект для каждой Activity.
////ViewModelComponent → объект живёт столько же, сколько ViewModel.


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    //    Аннотация @Provides
    //    Говорит: «вот функция, которая умеет создавать объект».
    @Provides
    fun provideHttpClient(): HttpClient {
        return HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                    }
                )
            }
        }
    }
}
