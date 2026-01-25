package com.example.capai.domain.repository

import android.content.Context
import android.net.Uri
import com.example.capai.domain.model.CapAI
import com.example.capai.domain.model.Length

interface CapAiRepository {
    suspend fun getImageCaption(uri: Uri?, length: Length, context: Context) : CapAI?
}