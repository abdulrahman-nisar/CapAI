package com.example.capai.domain.repository

import android.content.Context
import android.net.Uri
import com.example.capai.domain.model.CapAI
import com.example.capai.domain.model.Length
import kotlinx.coroutines.flow.Flow

interface CapAiRepository {
    suspend fun getImageCaption(uri: Uri?, length: Length, context: Context) : CapAI?
    suspend fun insertCapAI(capAI: CapAI, imageUri: Uri)
    fun getAllCaptions() : Flow<List<CapAI>>

    suspend fun deleteCapAI(capAI: CapAI)
}