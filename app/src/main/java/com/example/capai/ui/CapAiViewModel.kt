package com.example.capai.ui

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capai.domain.model.CapAI
import com.example.capai.domain.model.CaptionResult
import com.example.capai.domain.model.Length
import com.example.capai.domain.repository.CapAiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CapAiViewModel @Inject constructor(private val _capAIRepository : CapAiRepository) :
    ViewModel(){
    private val _result = MutableStateFlow(CaptionResult())
    var result = _result.asStateFlow()
    var imageUri = MutableStateFlow<Uri?>(null)
    private val _historyList = MutableStateFlow<List<CapAI>>(emptyList())
    val historyList = _historyList.asStateFlow()

    init {
       getHistoryList()
    }

    fun prepareForCaptionGeneration() {
        // Reset result and set loading state before navigating to DetailsScreen
        _result.value = CaptionResult(isGenerating = true)
    }

    fun getImageCaption(context: Context, length: Length) {
        viewModelScope.launch {
            _result.value = _result.value.copy(isGenerating = true)
            val caption = _capAIRepository.getImageCaption(imageUri.value, length, context)
            _result.value = _result.value.copy(capAI = caption, isGenerating = false)
            if(caption != null){
                addCaptionToHistory(caption)
            }
        }
    }

    fun addCaptionToHistory(capAI: CapAI){
        viewModelScope.launch {
            _capAIRepository.insertCapAI(capAI,imageUri.value!!)
        }
    }

    private fun getHistoryList(){
        viewModelScope.launch {
            _capAIRepository.getAllCaptions().collect {
                _historyList.value = it
            }
        }
    }
}