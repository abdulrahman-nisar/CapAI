package com.example.capai.Data.repository

import android.content.Context
import android.net.Uri
import com.example.capai.Data.remote.CapAIGeminiFireBase
import com.example.capai.Model.CapAI
import com.example.capai.Model.Length
import javax.inject.Inject

class CapAIRepository @Inject constructor(private val _gemini : CapAIGeminiFireBase){

    suspend fun getImageCaption(uri: Uri?, length: Length, context: Context) : CapAI?{
        return _gemini.geminiImageToCaption(uri,context, length)
    }


}