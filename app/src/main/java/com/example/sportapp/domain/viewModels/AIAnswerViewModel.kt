package com.example.sportapp.models.viewModels

import ChatRepository
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportapp.CleanArchitexture.data.repositories.AIAnswerRepository
import com.example.sportapp.CleanArchitexture.domain.models.aiAnswer.MessageEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AIAnswerViewModel : ViewModel(), BaseViewModelInterface<AnswerState, AIAnswerRepository> {

    override val state: MutableStateFlow<AnswerState> = MutableStateFlow(AnswerState.Load)

    override val repository = AIAnswerRepository()

    override fun getState(): StateFlow<AnswerState> = state

    override fun loadData() {

    }

    fun loadAIAnswer(prompt: String, chatRepository: ChatRepository, user: String) {
        viewModelScope.launch {
            try {
                val aiAnswer = repository.askIA(prompt, user)

                Log.d("tttAIAnswer", aiAnswer.text)
                state.value = AnswerState.AIAnswerContent(aiAnswer)

                CoroutineScope(Dispatchers.IO).launch {
                    chatRepository.addMessage(
                        MessageEntity(text = aiAnswer.text, sender = "AI", user = user), user
                    )
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
