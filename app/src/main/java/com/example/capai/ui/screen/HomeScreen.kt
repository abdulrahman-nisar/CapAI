package com.example.capai.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.capai.ui.CapAiViewModel
import com.example.capai.ui.screen.component.DrawerContent
import com.example.capai.ui.screen.component.HistoryListItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: CapAiViewModel,
    onNewBtnClick : () -> Unit,
    onHistoryItemClick: (Int) -> Unit
) {
    val historyList by viewModel.historyList.collectAsState()
    val drawerSate = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = {
            DrawerContent()
        },
        drawerState = drawerSate
    ) {
        Scaffold(
            modifier = Modifier,
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "CAPTION AI",
                            modifier = Modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )

                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFF1948a6),
                        titleContentColor = Color.White
                    ),
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    drawerSate.open()
                                }
                            }
                        ) {
                            Icon(
                                Icons.Default.Menu,"Menu",
                                tint = Color.White,
                                modifier = Modifier
                                    .padding(start = 8.dp)
                                    .size(30.dp)
                            )
                        }
                    }
                )
            },
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    onClick = onNewBtnClick,
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add",
                            tint = Color.White
                        )},
                    text = { Text(text = "New") },
                    containerColor = Color(0xFF1948a6),
                    contentColor = Color.White,
                    modifier = Modifier
                        .padding(16.dp)
                )
            }
        ) { innerpadding ->
            if (historyList.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerpadding),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No history yet\nTap 'New' to create your first caption",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Gray
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .padding(innerpadding)
                        .padding(top = 8.dp, bottom = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    itemsIndexed(historyList) { index, item ->
                        HistoryListItem(
                            capAI = item,
                            onClick = { onHistoryItemClick(index) }
                        )
                    }
                }
            }

        }
    }

}