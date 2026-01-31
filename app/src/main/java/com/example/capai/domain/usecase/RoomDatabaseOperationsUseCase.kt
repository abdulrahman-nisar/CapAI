package com.example.capai.domain.usecase

import android.content.Context
import android.net.Uri
import com.example.capai.domain.model.CapAI
import com.example.capai.domain.model.Length
import com.example.capai.domain.repository.CapAiRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomDatabaseOperationsUseCase @Inject constructor(
    val  repository: CapAiRepository
) {
    suspend fun getImageCaption(uri: Uri?, length: Length, context: Context) : CapAI?{
        return repository.getImageCaption(uri,length,context)
    }
    suspend fun insertCapAI(capAI: CapAI, imageUri: Uri){
        return repository.insertCapAI(capAI,imageUri)
    }
    fun getAllCaptions() : Flow<List<CapAI>>{
        return repository.getAllCaptions()
    }

    suspend fun deleteCapAI(capAI: CapAI){
        repository.deleteCapAI(capAI)
    }
}