package com.example.capai.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.capai.domain.model.CapAI
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeDetailsScreen(
    capAi : CapAI,
    onBackArrowClick: () -> Unit
) {
    BackHandler {
        onBackArrowClick()
    }

    val imageHeight = 400.dp
    val clipBoardManager = LocalClipboardManager.current
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    var selectedCaption = remember { AnnotatedString("") }
    Scaffold(
        snackbarHost = {
            SnackbarHost(snackBarHostState)
        },
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Details",
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp
                        )
                    }
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
                },
                actions = {
                    Spacer(modifier = Modifier.size(48.dp))
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .padding(12.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(imageHeight),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
                AsyncImage(
                    model = capAi.imageUri,
                    contentDescription = "Selected image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(imageHeight),
                    contentScale = ContentScale.Fit,
                    alignment = Alignment.Center
                )
            }

            Spacer(modifier = Modifier.height(25.dp))

            val captions = listOf(
                "Instagram" to (capAi.instagramCaption),
                "Facebook" to (capAi.facebookCaption),
                "Twitter" to (capAi.twitterCaption),
                "Pinterest" to (capAi.pinterestCaption),
                "LinkedIn" to (capAi.linkedinCaption),
                "Threads" to (capAi.threadCaption),
                "Snapchat" to (capAi.snapChatCaption),
                "TikTok" to (capAi.tiktokCaption)
            )

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
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth()
                                .background(color = when(platform) {
                                    "Instagram" -> Color(0xFFDA5D88)
                                    "Facebook" -> Color(0xFF1877F2)
                                    "Twitter" -> Color(0xFF1DA1F2)
                                    "Pinterest" -> Color(0xFFE60023)
                                    "LinkedIn" -> Color(0xFF0A66C2)
                                    "Threads" -> Color(0xFF000000)
                                    "Snapchat" -> Color(0xFFFFC107)
                                    "TikTok" -> Color(0xFF000000)
                                    else -> Color(0xFFE1306C)
                                }),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Icon(
                                painter = when (platform) {
                                    "Instagram" -> androidx.compose.ui.res.painterResource(id = com.example.capai.R.mipmap.instagram_foreground)
                                    "Facebook" -> androidx.compose.ui.res.painterResource(id = com.example.capai.R.mipmap.facebook_foreground)
                                    "Twitter" -> androidx.compose.ui.res.painterResource(id = com.example.capai.R.mipmap.twitter_foreground)
                                    "Pinterest" -> androidx.compose.ui.res.painterResource(id = com.example.capai.R.mipmap.pinterest_foreground)
                                    "LinkedIn" -> androidx.compose.ui.res.painterResource(id = com.example.capai.R.mipmap.linkldin_foreground)
                                    "Threads" -> androidx.compose.ui.res.painterResource(id = com.example.capai.R.mipmap.threads_foreground)
                                    "Snapchat" -> androidx.compose.ui.res.painterResource(id = com.example.capai.R.mipmap.snapchat_foreground)
                                    "TikTok" -> androidx.compose.ui.res.painterResource(id = com.example.capai.R.mipmap.tiktok_foreground)
                                    else -> androidx.compose.ui.res.painterResource(id = com.example.capai.R.mipmap.instagram_foreground)
                                },
                                contentDescription = "$platform Icon",
                                modifier = Modifier.size(40.dp),
                                tint = Color.Unspecified
                            )
                            Spacer(modifier = Modifier.size(8.dp))
                            Text(text = platform, fontWeight = FontWeight.Bold, fontSize = 16.sp)

                        }
                        Box(
                            modifier = Modifier
                                .background(
                                    color = when(platform) {
                                        "Instagram" -> Color(0xFFA86C80)
                                        "Facebook" -> Color(0xFF688BC8)
                                        "Twitter" -> Color(0xFF4D93B1)
                                        "Pinterest" -> Color(0xFFAA5E6E)
                                        "LinkedIn" -> Color(0xFF6186B3)
                                        "Threads" -> Color(0xFF635353)
                                        "Snapchat" -> Color(0xFFA1996D)
                                        "TikTok" -> Color(0xFF4C4141)
                                        else -> Color(0xFFFFE6F0)
                                    }
                                )
                        ){
                            Text(text = captionText!!, fontSize = 14.sp,
                                textAlign = TextAlign.Justify,
                                modifier = Modifier
                                    .padding(16.dp)
                                    .verticalScroll(scrollState))
                        }
                    }
                }
            }
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 8.dp)
                    ) {
                        captions.indices.forEach { index ->
                            val selected = pagerState.currentPage == index
                            if (selected) {
                                selectedCaption = AnnotatedString(captions[index].second!!)
                            }
                            Box(
                                modifier = Modifier
                                    .size(if (selected) 12.dp else 8.dp)
                                    .clip(CircleShape)
                                    .background(
                                        if (selected) Color(0xFF1948a6) else Color(0xFFE0E0E0)
                                    )
                            )
                            if (index != captions.lastIndex) {
                                Spacer(modifier = Modifier.size(8.dp))
                            }
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = {
                                clipBoardManager.setText(
                                    annotatedString = selectedCaption
                                )
                                scope.launch {
                                    snackBarHostState.showSnackbar("Caption copied to clipboard")
                                }
                            },
                            modifier = Modifier
                                .weight(1f)
                                .height(48.dp),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = "Copy",
                                fontWeight = FontWeight.Medium
                            )
                        }

                        Button(
                            onClick = {
                                // Share logic
                            },
                            modifier = Modifier
                                .weight(1f)
                                .height(48.dp),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = "Share",
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }



            }
        }

    }
}