package com.example.cinematch.ui.theme.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cinematch.data.Genre
import com.example.cinematch.data.RegisterRequest
import com.example.cinematch.ui.theme.utils.CustomButton
import com.example.cinematch.viewmodel.AuthenticationViewModel
import com.example.cinematch.viewmodel.GenreViewModel

@Composable
fun GenresSelectionScreen(navController: NavController, email: String, username: String, password: String, gender : String, age : String, modifier: Modifier = Modifier) {
    val genreViewModel: GenreViewModel = viewModel()
    val authViewModel: AuthenticationViewModel = viewModel()
    val genres by genreViewModel.genreResponse.observeAsState()
    val genreError by genreViewModel.errorMessage.observeAsState()
    var selectedGenres by remember { mutableStateOf(setOf<Int>()) }
    var genreSelectionError by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        genreViewModel.fetchGenres()
    }



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

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) // Make the list take up available vertical space
        ) {
            genres?.data?.let { genreList ->
                items(genreList) { genre ->
                    GenreItem(
                        genre = genre,
                        isSelected = selectedGenres.contains(genre.id),
                        onClick = {
                            selectedGenres = if (selectedGenres.contains(genre.id)) {
                                selectedGenres - genre.id
                            } else {
                                selectedGenres + genre.id
                            }
                            genreSelectionError = false
                        }
                    )
                }
            }
        }

        if (genreSelectionError) {
            Text(
                text = "Please select at least one genre",
                color = Color.Red,
                style = TextStyle(fontSize = 12.sp),
                modifier = Modifier.padding(top = 8.dp)
            )
        }
        genreError?.let {
            Text(
                text = it,
                color = Color.Red,
                style = TextStyle(fontSize = 12.sp),
                modifier = Modifier.padding(top = 10.dp).align(Alignment.Start)
            )
        }

        CustomButton(
            text = "Finish Sign Up",
            textColor = Color(0xff121212),
            backgroundColor = Color(0xff95acff),
            onClick = {
                if (selectedGenres.isEmpty()) {
                    genreSelectionError = true
                } else {
                    val registerRequest = RegisterRequest(
                        name = username,
                        email = email,
                        password = password,
                        gender = gender.uppercase(),
                        age = age.toInt(),
                        genre_preferences = selectedGenres.toList()
                    )
                    authViewModel.registerAndLogin(registerRequest, navController)
                }
            },
            modifier = Modifier.padding(top = 32.dp)
        )
    }
}

@Composable
fun GenreItem(genre: Genre, isSelected: Boolean, onClick: () -> Unit) {
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
            text = genre.name,
            color = if (isSelected) Color(0xff121212) else Color(0xfff3f3f3),
            style = TextStyle(fontSize = 18.sp)
        )
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
fun PreviewGenresSelectionScreen() {
    val navController = rememberNavController()
    GenresSelectionScreen(navController = navController, email = "", username = "", password = "", gender = "", age = "")
}
