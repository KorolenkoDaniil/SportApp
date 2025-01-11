package com.example.sportapp.models.youtube.api

import com.example.sportapp.models.youtube.domain.YoutubeSearchListResponseEntity
import com.example.sportapp.models.youtube.api.entities.youtube.PageInfo

class YoutubeRepository {

    //зашдущка
    fun getVideo(): List<YoutubeSearchListResponseEntity> {
        return listOf(
            YoutubeSearchListResponseEntity(
                kind = "youtube#searchListResponse",
                etag = "dummyEtag",
                nextPageToken = "dummyNextPageToken",
                regionCode = "US",
                pageInfo = PageInfo(
                    totalResults = 0,
                    resultsPerPage = 0
                ),
                items = listOf()
            )
        )
    }
}
