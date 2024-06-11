package com.example.cinematch.ui.theme.nav

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.compose.ui.unit.*

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val navItems = listOf(NavItem.Home, NavItem.Watchlist, NavItem.Watched, NavItem.Profile)
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }


    NavigationBar(
        containerColor = Color(0xFF121212),
        modifier = Modifier.fillMaxWidth().padding(0.dp)
    ) {
        navItems.forEachIndexed { index, item ->
            NavigationBarItem(
                alwaysShowLabel = true,
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = selectedItem == index,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF95ACFF),
                    selectedTextColor = Color(0xFF95ACFF),
                    unselectedIconColor = Color(0xFFFFFFFF),
                    unselectedTextColor = Color(0xFFFFFFFF)
                ),
                onClick = {
                    selectedItem = index
                    navController.navigate(item.path) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) { saveState = true }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
