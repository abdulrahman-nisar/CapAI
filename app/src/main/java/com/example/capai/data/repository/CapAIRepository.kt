package com.example.capai.data.repository

import android.content.Context
import android.net.Uri
import com.example.capai.data.remote.CapAIGeminiFireBase
import com.example.capai.domain.model.CapAI
import com.example.capai.domain.model.Length
import com.example.capai.domain.repository.CapAiRepository
import javax.inject.Inject

class CapAIRepositoryImpl @Inject constructor(private val _gemini : CapAIGeminiFireBase) : CapAiRepository {

    override suspend fun getImageCaption(uri: Uri?, length: Length, context: Context) : CapAI?{
        return _gemini.geminiImageToCaption(uri,context, length)
    }

}