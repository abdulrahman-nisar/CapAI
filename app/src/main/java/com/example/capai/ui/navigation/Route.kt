package com.example.capai.ui.navigation

import androidx.navigation3.runtime.NavKey
import com.example.capai.domain.model.Length
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route : NavKey{
    @Serializable
    data object HomeScreen : Route, NavKey
    @Serializable
    data object SelectImageScreen : Route, NavKey
    @Serializable
    data object CaptionPreferencesScreen : Route, NavKey
    @Serializable
    data class DetailsScreen(val selectedLength: Length) : Route, NavKey
    @Serializable
    data class HomeDetailsScreen(val itemIndex: Int) : Route, NavKey
}