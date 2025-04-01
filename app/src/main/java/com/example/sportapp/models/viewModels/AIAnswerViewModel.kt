package com.example.sportapp.models.viewModels

import ChatRepository
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportapp.models.chatMessage.MessageEntity
import com.example.sportapp.models.news.AIAnswerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AIAnswerViewModel : ViewModel(), AIAnswerViewModelInterface<AnswerState> {

    override val state: MutableStateFlow<AnswerState> = MutableStateFlow(AnswerState.Load)

    override  val AIAnswerRepository = AIAnswerRepository()

    override fun getState(): StateFlow<AnswerState> {
        return state
    }

    override fun loadData() {}

    fun loadAIAnswer(prompt: String,  repository: ChatRepository, user: String) {
        viewModelScope.launch {
            try {
                val AIanswer = AIAnswerRepository.askIA(prompt, user)
                Log.d("tttAIAnswer", AIanswer.text)

                state.value = AnswerState.AIAnswerContent(AIanswer)

                CoroutineScope(Dispatchers.IO).launch {
                    repository.addMessage(
                        MessageEntity(text = AIanswer.text, sender = "AI", user = user), user )
                }

            } catch (e: Throwable) {
                Log.e("tttAIAnswer", "Error loading news data: ${e.message}", e)
                state.value = AnswerState.Error(e)
            }
        }
    }
}

sealed interface AnswerState : BaseState {
    data object Load : AnswerState
    data class Error(val e: Throwable) : AnswerState
    data class AIAnswerContent(val answer: MessageEntity) : AnswerState
}
