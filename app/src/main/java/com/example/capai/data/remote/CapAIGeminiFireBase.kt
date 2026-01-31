package com.example.capai.data.remote

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import com.example.capai.domain.model.CapAI
import com.example.capai.domain.model.Length
import com.google.firebase.Firebase
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.GenerativeBackend
import com.google.firebase.ai.type.content
import javax.inject.Inject

class CapAIGeminiFireBase @Inject constructor() {

    suspend fun geminiImageToCaption(
        uri: Uri?,
        context: Context,
        length: Length = Length.SHORT
    ): CapAI? {

        val contentResolver = context.contentResolver
        if( uri != null) {
            val inputStream = contentResolver.openInputStream(uri)
            val bitmap: Bitmap = BitmapFactory.decodeStream(inputStream)
            inputStream?.close()
            val promptLength = when(length) {
                Length.SHORT -> "short"
                Length.LONG -> "long"
            }

            try{
                val Result = CapAI(
                    image = bitmap,
                    imageUri = uri,
                    instagramCaption = _generateResult(bitmap, promptLength, "Instagram"),
                    facebookCaption = _generateResult(bitmap, promptLength, "Facebook"),
                    twitterCaption = _generateResult(bitmap, promptLength, "Twitter"),
                    pinterestCaption = _generateResult(bitmap, promptLength, "Pinterest"),
                    linkedinCaption = _generateResult(bitmap, promptLength, "LinkedIn"),
                    threadCaption = _generateResult(bitmap, promptLength, "Threads"),
                    snapChatCaption = _generateResult(bitmap, promptLength, "Snapchat"),
                    tiktokCaption = _generateResult(bitmap, promptLength, "TikTok"),
                    isSuccess = true
                )
                return Result
            }catch (e: Exception) {
                return CapAI(
                    isSuccess = false,
                    errorMessage = e.message ?: "Unknown error occurred"
                )
            }

        }

        return CapAI(
            isSuccess = false,
            errorMessage = "Image URI is null"
        )


    }

    private suspend fun _generateResult(bitmap: Bitmap, length: String, platform: String): String {

        return try {
            val model = Firebase.ai(backend = GenerativeBackend.googleAI())
                .generativeModel("gemini-2.5-flash-lite")
            val prompt = content {
                image(bitmap)
                text(
                    "Generate a $length caption for the given image that is suitable for posting" +
                            " on $platform. Be specific and directly give the final caption. Do not include" +
                            " explanations, suggestions, or any formatting like bold or italics. I will " +
                            "directly use the result, so only return the caption text."
                )

            }
            model.generateContent(prompt).text ?: "Error generating caption for $platform"
        }catch (e: Exception) {
           throw e
        }
    }
}