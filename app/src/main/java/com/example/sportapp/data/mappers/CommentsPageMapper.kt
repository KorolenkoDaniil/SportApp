package com.example.sportapp.CleanArchitexture.data.mappers

import com.example.sportapp.CleanArchitexture.data.dto.comments.CommentsPageDto
import com.example.sportapp.CleanArchitexture.domain.models.comments.CommentsPageEntity


class CommentsPageMapper {

    private val commentsMapper = CommentsMapper()

    fun getListComments(response: CommentsPageDto): CommentsPageEntity {
        return CommentsPageEntity(
            pageNumber = response.pageNumber,
            pageSize = response.pageSize,
            totalItems = response.totalItems,
            totalPages = response.totalPages,
            comments = commentsMapper.convertToListEntity(response.comments)
        )
    }




}