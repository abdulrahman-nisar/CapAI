package com.example.capai.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.capai.ui.CapAiViewModel
import com.example.capai.ui.screen.CaptionPreferencesScreen
import com.example.capai.ui.screen.DetailsScreen
import com.example.capai.ui.screen.HomeDetailsScreen
import com.example.capai.ui.screen.HomeScreen
import com.example.capai.ui.screen.SelectImageScreen
import androidx.compose.runtime.collectAsState


@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
@Composable
fun NavigationRoot(viewModel: CapAiViewModel) {

    val backStack = rememberNavBackStack(Route.HomeScreen)

    NavDisplay(
        backStack,
        transitionSpec = {
            ContentTransform(
                targetContentEnter = EnterTransition.None,
                initialContentExit = ExitTransition.None
            )
        },
        popTransitionSpec = {
            ContentTransform(
                targetContentEnter = EnterTransition.None,
                initialContentExit = ExitTransition.None
            )
        },
        entryProvider = entryProvider{
            entry<Route.HomeScreen> {
                HomeScreen(
                    viewModel = viewModel,
                    onNewBtnClick = {
                        backStack.add(Route.SelectImageScreen)
                    },
                    onHistoryItemClick = { index ->
                        backStack.add(Route.HomeDetailsScreen(index))
                    }
                )
            }
            entry<Route.HomeDetailsScreen> { route ->
                val historyList = viewModel.historyList.collectAsState().value
                if (route.itemIndex in historyList.indices) {
                    HomeDetailsScreen(
                        capAi = historyList[route.itemIndex],
                        onBackArrowClick = {
                            backStack.removeLast()
                        }
                    )
                }
            }
            entry<Route.SelectImageScreen> {
                 SelectImageScreen(
                     viewModel = viewModel,
                     onBackArrowClick = {
                            backStack.removeLast()
                     },
                     onSucessfulImagePick = {
                            backStack.add(Route.CaptionPreferencesScreen)
                     }
                 )
            }
            entry <Route.CaptionPreferencesScreen> {
                CaptionPreferencesScreen(
                    viewModel = viewModel,
                    onBackArrowClick = {
                        backStack.removeLast()
                    },
                    onGenerateCaptionClick = {
                        backStack.add(Route.DetailsScreen(it))
                    }
                )
            }
            entry <Route.DetailsScreen> { len->
                DetailsScreen(
                    viewModel = viewModel,
                    len.selectedLength,
                    onBackArrowClick = {
                        backStack.removeLast()
                        backStack.removeLast()
                        backStack.removeLast()
                    },
                    onIsSuccessFalse = {
                        backStack.removeLast()
                    }
                )
            }

        }

    )

}