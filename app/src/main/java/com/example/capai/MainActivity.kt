package com.example.capai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.capai.ui.screen.SelectImageScreen
import com.example.capai.ui.CapAiViewModel
import com.example.capai.ui.theme.CapAiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CapAiTheme {
                val viewModel: CapAiViewModel = viewModel()
                SelectImageScreen(viewModel)
    //
//                Scaffold { innerPadding ->
//                    Box(
//                        modifier = Modifier
//                            .padding(innerPadding)
//                    ) {
//                        var caption by remember { mutableStateOf<String?>(null) }
//                        val con text = LocalContext.current
//                        var URI by remember { mutableStateOf<Uri?>(null) }
//                        val capAI by viewModel.capAI
//

//                        Button (onClick = {
//                            URI = null
//                        }) {
//                            Text(text = "Pick Image")
//                        }
//                        LaunchedEffect(URI) {
//                            URI?.let {
//                                viewModel.getImageCaption(it, context, Length.SHORT)
//                            }
//                        }
//
//                        LaunchedEffect(capAI) {
//                            capAI?.let {
//                                caption = it.instagramCaption
//
//                            }
//
//                        }
//
//                        Text(caption?:"loading")
//
//                    }
//                }

            }
        }
    }
}


