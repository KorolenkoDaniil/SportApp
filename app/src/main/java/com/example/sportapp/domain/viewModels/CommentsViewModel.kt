package com.example.sportapp.models.viewModels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportapp.CleanArchitexture.data.repositories.CommentRepository
import com.example.sportapp.CleanArchitexture.domain.models.comments.CommentEntity
import com.example.sportapp.CleanArchitexture.domain.models.comments.CommentsPageEntity
import com.example.sportapp.CleanArchitexture.domain.models.user.UserEntity
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


    fun loadData(newsDateTime: LocalDateTime, pageNumber: Int, viewer: String) {
        viewModelScope.launch {
            try {
                val comments: CommentsPageEntity? = repository.getComments(
                    newsDateTime = newsDateTime,
                    pageNumber = pageNumber,
                    viewer
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


    suspend fun loadComments (newsDateTime: LocalDateTime, pageNumber: Int, viewer: String): List<CommentEntity> {

        return repository.getComments(newsDateTime, pageNumber, viewer )?.comments ?:  emptyList()
    }




    fun putLike(likeAuthorEmail: String, commentId: Int): Int{
        Log.d("commentID", commentId.toString())

        if (likeAuthorEmail.isBlank())
            return -1

        var commentLike = 0

        viewModelScope.launch {
            try {
                commentLike = repository.putCommentLike(
                    likeAuthorEmail = likeAuthorEmail,
                    commentId = commentId
                )

            } catch (e: Throwable) {
                state.value = CommentState.Error(e)
            }
        }

        return commentLike
    }



    fun removeLike(likeAuthorEmail: String, commentId: Int): Int{
        Log.d("commentID", commentId.toString())

        if (likeAuthorEmail.isBlank())
            return -1

        var commentLike = 0

        viewModelScope.launch {
            try {
                commentLike = repository.removeCommentLike(
                    likeAuthorEmail = likeAuthorEmail,
                    commentId = commentId
                )

            } catch (e: Throwable) {
                state.value = CommentState.Error(e)
            }
        }

        return commentLike
    }



    fun toggleLike(
        lastLikeTime: MutableState<Long>,
        isLiked: MutableState<Boolean>,
        user: UserEntity,
        commentId: Int,
        comment: CommentEntity,
        likesCount: MutableState<Int>
    ) {
        val likeCoolDown = 2000L
        val currentTime = System.currentTimeMillis()

        if (currentTime - lastLikeTime.value >= likeCoolDown) {
            viewModelScope.launch {
                if (isLiked.value) {
                    removeLike(
                        likeAuthorEmail = user.email,
                        commentId = commentId
                    )
                    likesCount.value -= 1
                } else {
                    putLike(
                        likeAuthorEmail = user.email,
                        commentId = commentId
                    )
                    likesCount.value += 1
                }
                lastLikeTime.value = currentTime
                isLiked.value = !isLiked.value
                comment.isLiked = !comment.isLiked
            }
        }
    }





}


sealed interface CommentState : BaseState {
    data object Load : CommentState
    data class Error(val e: Throwable) : CommentState
    data class CommentsContent (val comments: CommentsPageEntity) : CommentState
}