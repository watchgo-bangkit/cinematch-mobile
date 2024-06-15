package com.example.cinematch.ui.theme.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cinematch.ui.theme.utils.WatchListCard
import com.example.cinematch.R

// Data class to hold movie information
data class MovieData(
    val imageId: Int,
    val title: String,
    val genres: List<String>,
    val duration: String,
    val year: String,
    val rating: String
)

@Composable
fun WatchListScreen(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally, // Center horizontally
    ) {
        Text(
            "Watchlist",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterHorizontally) // Center the title horizontally
                .padding(bottom = 16.dp)
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally // Center items within the LazyColumn
        ) {
            // Example data setup
            val movies = listOf(
                MovieData(R.drawable.jedi, "John Wick: Chapter 4", listOf("Action", "Thriller", "Crime"), "2 hr 50 min", "2023", "8.5"),
                MovieData(R.drawable.jedi, "Inception", listOf("Sci-Fi", "Thriller"), "2 hr 28 min", "2010", "8.8"),
                MovieData(R.drawable.jedi, "Interstellar", listOf("Adventure", "Drama", "Sci-Fi"), "2 hr 49 min", "2014", "8.6")
            )

            items(movies.size) { index ->
                WatchListCard(
                    imageId = movies[index].imageId,
                    title = movies[index].title,
                    genres = movies[index].genres,
                    duration = movies[index].duration,
                    year = movies[index].year,
                    rating = movies[index].rating
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun WatchListScreenPreview() {
    val navController = rememberNavController()
    MaterialTheme {
        WatchListScreen(navController)
    }
}
