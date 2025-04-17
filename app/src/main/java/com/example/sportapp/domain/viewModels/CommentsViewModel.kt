package com.example.sportapp.models.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportapp.CleanArchitexture.data.repositories.CommentRepository
import com.example.sportapp.CleanArchitexture.domain.models.comments.CommentEntity
import com.example.sportapp.CleanArchitexture.domain.models.comments.CommentsPageEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime


class CommentsViewModel : ViewModel(), BaseViewModelInterface<CommentState, CommentRepository> {

    override val state: MutableStateFlow<CommentState> = MutableStateFlow(CommentState.Load)

    override val repository = CommentRepository()

    override fun loadData() {}

    private var _commentsCount = commentsCount

    var commentsCount: Int
        get() = _commentsCount
        set(value) {
            _commentsCount = value
        }


    fun loadData(newsDateTime: LocalDateTime, pageNumber: Int) {
        viewModelScope.launch {
            try {
                val comments: CommentsPageEntity? = repository.getComments(
                    newsDateTime = newsDateTime,
                    pageNumber = pageNumber
                )
                if (comments != null) {

                    var content = CommentState.CommentsContent(comments)

                    state.value = content

                    commentsCount = content.comments.totalItems

                } else {
                    state.value = CommentState.Error(Exception("Failed to load comments"))
                }
            } catch (e: Throwable) {
                state.value = CommentState.Error(e)
            }
        }
    }

    fun putComment(comment: CommentEntity): CommentEntity {

        var newComment: CommentEntity = comment

        viewModelScope.launch {
            try {
                val comment1: CommentEntity = repository.putComment(comment)

                if (comment1 != null) {

                    newComment = comment1
                } else {
                    state.value = CommentState.Error(Exception("Failed to load comments"))
                }
            } catch (e: Throwable) {
                state.value = CommentState.Error(e)
            }
        }

        return newComment
    }



    suspend fun loadComments (newsDateTime: LocalDateTime, pageNumber: Int): List<CommentEntity> {

        return repository.getComments(newsDateTime, pageNumber)?.comments ?:  emptyList()
    }




}


sealed interface CommentState : BaseState {
    data object Load : CommentState
    data class Error(val e: Throwable) : CommentState
    data class CommentsContent (val comments: CommentsPageEntity) : CommentState
}