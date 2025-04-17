package com.example.sportapp.CleanArchitexture.data.repositories

import android.util.Log
import com.example.sportapp.CleanArchitexture.data.dto.news.NewsDto
import com.example.sportapp.CleanArchitexture.data.dto.news.NewsPageDto
import com.example.sportapp.CleanArchitexture.data.mappers.NewsMapper
import com.example.sportapp.CleanArchitexture.domain.models.news.NewsEntity
import com.example.sportapp.CleanArchitexture.domain.models.news.NewsListEntity
import com.example.sportapp.data.BaseUrl
import com.example.sportapp.data.network.newsNetworkClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import io.ktor.http.HttpMethod
import io.ktor.http.encodedPath
import kotlinx.serialization.json.Json

class NewsRepository {

    private val controllerPath = "NewsController"
    private val getNewsController = "GetNews"
    private val getOneNewsController = "GetOneNews"

    val newsMapper = NewsMapper()

    private val json = Json {
        this.ignoreUnknownKeys = true
    }


    suspend fun getNews(pageNumber: Int): NewsListEntity {
        val builder = HttpRequestBuilder()

        builder.method = HttpMethod.Get

        builder.url {
            (BaseUrl)
            encodedPath = "/$controllerPath/$getNewsController"
            this.parameters.append("pageNumber", pageNumber.toString())
        }

        val requestUrl = builder.url.toString()
        Log.d("tttNews", "Request URL: $requestUrl")

        val response = newsNetworkClient.request(builder)

        Log.d("tttNews", response.toString())

        val responseString: String = response.body()

        Log.d("tttNews", responseString)

        val newsPageDto: NewsPageDto = json.decodeFromString(responseString)

        return newsMapper.getNewsEntityList(newsPageDto, BaseUrl)
    }



    suspend fun getOneNews(dateTime: String, userEmail: String): NewsEntity {
        val builder = HttpRequestBuilder()

        builder.method = HttpMethod.Get

        builder.url {
            (BaseUrl)
            encodedPath = "/$controllerPath/$getOneNewsController"
            this.parameters.append("dateTime", dateTime)
            this.parameters.append("userEmail", userEmail)

        }

        val requestUrl = builder.url.toString()
        Log.d("tttNews", "Request URL: $requestUrl")

        val response = newsNetworkClient.request(builder)

        Log.d("tttNews", response.toString())

        val responseString: String = response.body()

        Log.d("tttNews", responseString)

        val oneNewsResponse: NewsDto = json.decodeFromString(responseString)

        return newsMapper.getOneNewsEntity(oneNewsResponse, BaseUrl)
    }
}






