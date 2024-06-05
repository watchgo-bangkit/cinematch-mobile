package com.example.cinematch

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cinematch.ui.theme.screen.GenresSelectionScreen
import com.example.cinematch.ui.theme.screen.HomeScreen
import com.example.cinematch.ui.theme.screen.LandingScreen
import com.example.cinematch.ui.theme.screen.LoginScreen
import com.example.cinematch.ui.theme.screen.SignUpScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "landing") {
        composable("landing") { LandingScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("signup") { SignUpScreen(navController) }
        composable(
            "genresSelection/{email}/{username}/{password}",
            arguments = listOf(
                navArgument("email") { type = NavType.StringType },
                navArgument("username") { type = NavType.StringType },
                navArgument("password") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            val username = backStackEntry.arguments?.getString("username") ?: ""
            val password = backStackEntry.arguments?.getString("password") ?: ""
            GenresSelectionScreen(navController = navController, email = email, username = username, password = password)
        }
        composable("home") { HomeScreen(navController) }
    }
}
