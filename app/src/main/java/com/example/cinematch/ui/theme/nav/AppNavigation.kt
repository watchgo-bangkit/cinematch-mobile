package com.example.cinematch.ui.theme.nav

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cinematch.api.TokenManager
import com.example.cinematch.ui.theme.screen.GenresSelectionScreen
import com.example.cinematch.ui.theme.screen.HomeScreen
import com.example.cinematch.ui.theme.screen.LandingScreen
import com.example.cinematch.ui.theme.screen.LoginScreen
import com.example.cinematch.ui.theme.screen.MovieDetailScreen
import com.example.cinematch.ui.theme.screen.ProfileScreen
import com.example.cinematch.ui.theme.screen.SignUpScreen
import com.example.cinematch.ui.theme.screen.WatchListScreen
import com.example.cinematch.ui.theme.screen.WatchedScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val tokenManager = remember { TokenManager(context) }
    var startDestination by remember { mutableStateOf<String?>(null) }
    LaunchedEffect(Unit) {
        startDestination = if (tokenManager.isTokenValid()) {
            NavItem.Home.path
        } else {
            "landing"
        }
    }

    if (startDestination != null) {
        Scaffold(
            bottomBar = {
                val currentBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = currentBackStackEntry?.destination?.route
                if (shouldShowBottomBar(currentRoute)) {
                    BottomAppBar(
                        containerColor = Color(0xFF121212),
                    ) { BottomNavigationBar(navController = navController) }
                }
            }
        ) {
            NavigationGraph(
                navController = navController,
                startDestination = startDestination!!
            )
        }
    }
}

@Composable
fun shouldShowBottomBar(currentRoute: String?): Boolean {
    return currentRoute in listOf(
        NavItem.Home.path,
        NavItem.Watchlist.path,
        NavItem.Watched.path,
        NavItem.Profile.path
    )
}

@Composable
fun NavigationGraph(navController: NavHostController, startDestination: String) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable("landing") { LandingScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("signup") { SignUpScreen(navController) }
        composable(
            "genresSelection/{email}/{username}/{password}/{gender}/{age}",
            arguments = listOf(
                navArgument("email") { type = NavType.StringType },
                navArgument("username") { type = NavType.StringType },
                navArgument("password") { type = NavType.StringType },
                navArgument("gender") { type = NavType.StringType },
                navArgument("age") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            val username = backStackEntry.arguments?.getString("username") ?: ""
            val password = backStackEntry.arguments?.getString("password") ?: ""
            val gender = backStackEntry.arguments?.getString("gender") ?: ""
            val age = backStackEntry.arguments?.getString("age") ?: ""
            GenresSelectionScreen(navController = navController, email = email, username = username, password = password, gender = gender, age = age)
        }
        composable(NavItem.Home.path) { HomeScreen(navController) }
        composable(NavItem.Watchlist.path) { WatchListScreen(navController) }
        composable(NavItem.Watched.path) { WatchedScreen(navController) }
        composable(NavItem.Profile.path) { ProfileScreen(navController) }
        composable(
            "movieDetail/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId")
            movieId?.let {
                MovieDetailScreen(navController = navController, movieId = it)
            }
        }
    }
}
