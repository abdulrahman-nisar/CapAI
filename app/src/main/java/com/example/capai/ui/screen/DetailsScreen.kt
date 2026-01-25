package com.example.capai.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.capai.domain.model.Length
import com.example.capai.ui.CapAiViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    viewModel: CapAiViewModel,
    selectedLength: Length,
    onBackArrowClick: () -> Unit
){
    val imageHeight = 490.dp
    val result by viewModel.result.collectAsState()
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Details",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1948a6),
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = onBackArrowClick) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
            )
        }
    ) {
        innerPadding->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .padding(12.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            viewModel.getImageCaption(context, selectedLength)
            if(result.isGenerating){
                CircularProgressIndicator()
            }else{
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(imageHeight),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                ) {
                    AsyncImage(
                        model = viewModel.imageUri.collectAsState().value,
                        contentDescription = "Selected image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(imageHeight),
                        contentScale = ContentScale.Fit,
                        alignment = Alignment.Center
                    )
                }

                Spacer(modifier = Modifier.height(25.dp))

                val capAI = result.capAI
                val captions = listOf(
                    "Instagram" to (capAI?.instagramCaption ?: ""),
                    "Facebook" to (capAI?.facebookCaption ?: ""),
                    "Twitter" to (capAI?.twitterCaption ?: ""),
                    "Pinterest" to (capAI?.pinterestCaption ?: ""),
                    "LinkedIn" to (capAI?.linkedinCaption ?: ""),
                    "Threads" to (capAI?.threadCaption ?: ""),
                    "Snapchat" to (capAI?.snapChatCaption ?: ""),
                    "TikTok" to (capAI?.tiktokCaption ?: "")
                ).filter { it.second.isNotBlank() }

                if (captions.isEmpty()) {
                    Text(text = "No captions generated", fontSize = 16.sp)
                } else {

                    val pagerState = rememberPagerState { captions.size }

                    HorizontalPager(
                        pagerState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                    ) { page ->
                        val (platform, captionText) = captions[page]
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            shape = RoundedCornerShape(8.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(12.dp)
                                    .fillMaxWidth(),
                                verticalArrangement = Arrangement.Top,
                                horizontalAlignment = Alignment.Start
                            ) {
                                Text(text = platform, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(text = captionText, fontSize = 14.sp)
                            }
                        }
                    }

                    Row(
                        modifier = Modifier.padding(top = 12.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        for (i in captions.indices) {
                            val selected = pagerState.currentPage == i
                            Box(
                                modifier = Modifier
                                    .size(if (selected) 10.dp else 8.dp)
                                    .clip(CircleShape)
                                    .background(if (selected) Color(0xFF1948a6) else Color.LightGray)
                            )
                            if (i != captions.lastIndex) Spacer(modifier = Modifier.size(6.dp))
                        }
                    }
                }

            }

        }
    }

}