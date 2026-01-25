package com.example.capai.ui

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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
    fun getImageCaption(context: Context, length: Length) {
        _result.value.isGenerating = true
        viewModelScope.launch {
            _result.value.capAI = _capAIRepository.getImageCaption(imageUri.value, length, context)
        }
        if(_result.value.capAI != null){
            _result.value.isGenerating = false
        }
    }
}