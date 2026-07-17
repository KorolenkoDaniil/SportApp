package com.example.sportapp.support2026.features.news.data.api

import android.util.Log
import com.example.sportapp.support2026.app.baseUrl
import com.example.sportapp.support2026.features.news.data.dto.newsDetails.NewsDetailsDto
import com.example.sportapp.support2026.features.news.data.dto.newsList.NewsPageDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import javax.inject.Inject

class NewsNetworkProvider @Inject constructor(
    private  val client: HttpClient
) {

    suspend fun getPaginatedNews(pageNumber: Int) : NewsPageDto {

        val httpResponse: HttpResponse = client.get("${baseUrl}/NewsController/GetNews") {
            url {
                parameters.append("pageNumber", pageNumber.toString())
                parameters.append("pageSize", "10")
            }
        }

        val response : NewsPageDto = httpResponse.body()
        Log.d("tttNews", "${response}")

        return response
    }

    suspend fun getNewsDetails(newsId: Int): NewsDetailsDto {
        val httpResponse: HttpResponse = client.get ( "${baseUrl}/NewsController/GetNewsDetails"){
            url{
                parameters.append("newsId", newsId.toString())
            }
        }

        val response : NewsDetailsDto = httpResponse.body()
        Log.d("tttNews", "${response}")

        return response
    }

}