package com.example.cinematch.ui.theme.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cinematch.ui.theme.utils.CustomButton

@Composable
fun GenresSelectionScreen(navController: NavController, email: String, username: String, password: String, modifier: Modifier = Modifier) {
    val genres = listOf("Action", "Comedy", "Drama", "Horror", "Romance", "Sci-Fi", "Thriller") // DUMMY DOANG
    var selectedGenres by remember { mutableStateOf(setOf<String>()) }
    var genreError by remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xff121212))
            .padding(16.dp)
    ) {
        Text(
            text = "Select Your Favorite Movie Genres",
            color = Color(0xfff3f3f3),
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 32.dp)
        )

        genres.forEach { genre ->
            GenreItem(
                genre = genre,
                isSelected = selectedGenres.contains(genre),
                onClick = {
                    selectedGenres = if (selectedGenres.contains(genre)) {
                        selectedGenres - genre
                    } else {
                        selectedGenres + genre
                    }
                    genreError = false
                }
            )
        }

        if (genreError) {
            Text(
                text = "Please select at least one genre",
                color = Color.Red,
                style = TextStyle(fontSize = 12.sp),
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        CustomButton(
            text = "Finish Sign Up",
            textColor = Color(0xff121212),
            backgroundColor = Color(0xff95acff),
            onClick = {
                if (selectedGenres.isEmpty()) {
                    genreError = true
                } else {
                    // TODO : SIGN UP AND NAVIGATE TO HOME
                }

            },
            modifier = Modifier.padding(top = 32.dp)
        )
    }
}

@Composable
fun GenreItem(genre: String, isSelected: Boolean, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick)
            .background(
                if (isSelected) Color(0xff95acff) else Color(0xff1e1e1e),
                shape = RoundedCornerShape(30.dp)
            )
            .padding(16.dp)
    ) {
        Text(
            text = genre,
            color = if (isSelected) Color(0xff121212) else Color(0xfff3f3f3),
            style = TextStyle(fontSize = 18.sp)
        )
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
fun PreviewGenresSelectionScreen() {
    val navController = rememberNavController()
    GenresSelectionScreen(navController = navController, email = "", username = "", password = "")
}
