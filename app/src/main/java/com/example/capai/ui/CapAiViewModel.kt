package com.example.capai.ui

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capai.Data.repository.CapAIRepository
import com.example.capai.Model.CapAI
import com.example.capai.Model.Length
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CapAiViewModel @Inject constructor(private val _capAIRepository : CapAIRepository) :
    ViewModel(){

    private val _capAI = mutableStateOf<CapAI?>(null)
    var capAI : State<CapAI?> = _capAI
    var imageUri = mutableStateOf<Uri?>(null)
    fun getImageCaption(context: Context, length: Length)
    {
        viewModelScope.launch {
            _capAI.value = _capAIRepository.getImageCaption(imageUri.value, length, context)
        }

    }


}