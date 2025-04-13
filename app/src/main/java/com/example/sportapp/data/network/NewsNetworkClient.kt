package com.example.sportapp.data.network

import android.util.Log
import com.example.sportapp.CleanArchitexture.BaseUrl
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpHeaders

val newsNetworkClient = HttpClient {
    defaultRequest {
        this.url(BaseUrl)
    }

    HttpResponseValidator {
        validateResponse { response: HttpResponse ->
            val contentType = response.headers[HttpHeaders.ContentType]
            if (contentType != null) {

                val charset = contentType.split("charset=").getOrNull(1) ?: "Unknown"
                Log.d("tttNews", "Response Charset: $charset")
            } else {
                Log.d("tttNews", "Content-Type header not found")
            }
        }
    }
}