package com.example.cinematch.ui.theme.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.PlaylistAdd
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person

sealed class NavItem {
    object Home :
        Item(path = NavPath.HOME.toString(), title = NavTitle.HOME, icon = Icons.Default.Home)

    object Watchlist :
        Item(path = NavPath.WATCHLIST.toString(), title = NavTitle.WATCHLIST, icon = Icons.AutoMirrored.Filled.PlaylistAdd)

    object Watched :
        Item(path = NavPath.WATCHED.toString(), title = NavTitle.WATCHED, icon = Icons.Default.CheckCircle)

    object Profile :
        Item(
            path = NavPath.PROFILE.toString(), title = NavTitle.PROFILE, icon = Icons.Default.Person)
}