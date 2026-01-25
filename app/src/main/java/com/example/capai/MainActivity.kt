package com.example.capai

import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.capai.domain.model.Length
import com.example.capai.ui.screen.SelectImageScreen
import com.example.capai.ui.CapAiViewModel
import com.example.capai.ui.navigation.NavigationRoot
import com.example.capai.ui.screen.HomeScreen
import com.example.capai.ui.theme.CapAiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CapAiTheme {
                val viewModel: CapAiViewModel = viewModel()
                NavigationRoot(viewModel)

//                Scaffold { innerPadding ->
//                    Box(
//                        modifier = Modifier
//                            .padding(innerPadding)
//                    ) {
//                        var caption by remember { mutableStateOf<String?>(null) }
//                        val context = LocalContext.current
//                        var URI by remember { mutableStateOf<Uri?>(null) }
//                        val capAI by viewModel.capAI
//
//
//                        Button (onClick = {
//
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
//
         }
      }
  }

}




