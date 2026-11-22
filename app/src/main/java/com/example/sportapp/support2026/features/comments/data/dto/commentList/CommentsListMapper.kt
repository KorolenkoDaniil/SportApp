package com.example.sportapp.support2026.features.comments.data.dto.commentList

import com.example.sportapp.support2026.features.comments.data.dto.comment.CommentMapper
import com.example.sportapp.support2026.features.comments.domain.entities.CommentsPage

object CommentsListMapper {
    fun MapCommentsListDtoToEntity (dto: CommentDtoPage) : CommentsPage {
        return CommentsPage(
            pageNumber = dto.pageNumber,
            pageSize = dto.pageSize,
            totalItems = dto.totalItems,
            totalPages = dto.totalPages,
            itemsList = CommentMapper.mapList(dto.itemsList)
        )
    }
}