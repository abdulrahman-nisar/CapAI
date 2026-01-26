package com.example.capai.data.repository

import android.content.Context
import android.net.Uri
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
import com.example.capai.data.local.dao.CapAIDao
import com.example.capai.data.local.entity.CaptionEntity
import com.example.capai.data.remote.CapAIGeminiFireBase
import com.example.capai.domain.model.CapAI
import com.example.capai.domain.model.Length
import com.example.capai.domain.repository.CapAiRepository
import com.google.firebase.Timestamp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CapAIRepositoryImpl @Inject constructor(
    private val _gemini : CapAIGeminiFireBase,
    private val dao : CapAIDao
) : CapAiRepository {

    override suspend fun getImageCaption(uri: Uri?, length: Length, context: Context) : CapAI?{
        return _gemini.geminiImageToCaption(uri,context, length)
    }

    override suspend fun insertCapAI(capAI: CapAI, imageUri : Uri) {
        dao.addCaption(CaptionEntity(
            imageUri = imageUri.toString(),
            timestamp = System.currentTimeMillis().toLong(),
            id = 0,
            instagramCaption = capAI.instagramCaption!!,
            facebookCaption = capAI.facebookCaption!!,
            twitterCaption = capAI.twitterCaption!!,
            pinterestCaption = capAI.pinterestCaption!!,
            linkedinCaption = capAI.linkedinCaption!!,
            threadCaption = capAI.threadCaption!!,
            snapChatCaption = capAI.snapChatCaption!!,
            tiktokCaption = capAI.tiktokCaption!!,
        ))
    }

    override fun getAllCaptions(): Flow<List<CapAI>> {
        return dao.getAllCaptions().map { list ->
            list.map { entity ->
                CapAI(
                    imageUri = entity.imageUri.toUri(),
                    instagramCaption = entity.instagramCaption,
                    facebookCaption = entity.facebookCaption,
                    twitterCaption = entity.twitterCaption,
                    pinterestCaption = entity.pinterestCaption,
                    linkedinCaption = entity.linkedinCaption,
                    threadCaption = entity.threadCaption,
                    snapChatCaption = entity.snapChatCaption,
                    tiktokCaption = entity.tiktokCaption
                )
            }
        }
    }

}