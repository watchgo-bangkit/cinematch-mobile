package com.example.cinematch.ui.theme.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search

sealed class NavItem {
    object Home :
        Item(path = NavPath.HOME.toString(), title = NavTitle.HOME, icon = Icons.Default.Home)

    object Watchlist :
        Item(path = NavPath.WATCHLIST.toString(), title = NavTitle.WATCHLIST, icon = Icons.Default.Search)

    object Watched :
        Item(path = NavPath.WATCHED.toString(), title = NavTitle.WATCHED, icon = Icons.Default.List)

    object Profile :
        Item(
            path = NavPath.PROFILE.toString(), title = NavTitle.PROFILE, icon = Icons.Default.Person)
}