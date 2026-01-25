package com.example.capai.domain.usecase

import android.content.Context
import android.net.Uri
import com.example.capai.domain.model.CapAI
import com.example.capai.domain.model.Length
import com.example.capai.domain.repository.CapAiRepository
import javax.inject.Inject

class GetImageCapUseCase @Inject constructor(
   val  repository: CapAiRepository
){
    suspend operator fun invoke(uri: Uri? , length: Length , context: Context): CapAI?{
        return repository.getImageCaption(uri,length,context)
    }
}