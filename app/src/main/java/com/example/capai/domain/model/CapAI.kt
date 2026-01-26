package com.example.capai.domain.model

import android.graphics.Bitmap
import android.net.Uri

data class CapAI(
    var image: Bitmap? = null,
    var instagramCaption: String? = "",
    var facebookCaption: String? = "",
    var twitterCaption: String? = "",
    var pinterestCaption: String? = "",
    var linkedinCaption: String? = "",
    var threadCaption: String? = "",
    var snapChatCaption: String? = "",
    var tiktokCaption: String? = "",
    var imageUri : Uri? = null
)