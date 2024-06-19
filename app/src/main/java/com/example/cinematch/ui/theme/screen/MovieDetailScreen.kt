package com.example.cinematch.ui.theme.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.cinematch.BuildConfig
import com.example.cinematch.viewmodel.MovieDetailViewModel

@Composable
fun MovieDetailScreen(navController: NavController, movieId: Int, viewModel: MovieDetailViewModel = viewModel()) {

    val movieDetail by viewModel.movieDetail.observeAsState()
    val loading by viewModel.loading.observeAsState(false)
    val errorMessage by viewModel.errorMessage.observeAsState("")

    LaunchedEffect(movieId) {
        viewModel.fetchMovieDetail(movieId)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
                Text(
                    text = "Movie Details",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }

        if (loading) {
            item {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = Color.White)
                }
            }
        } else if (errorMessage.isNotEmpty()) {
            item {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = errorMessage,
                        color = Color.White,
                        fontSize = 18.sp
                    )
                }
            }
        } else {
            movieDetail?.let { movie ->
                item {
                    Image(
                        painter = rememberAsyncImagePainter("${BuildConfig.ASSET_URL}${movie.data.backdrop_path}"),
                        contentDescription = movie.data.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = movie.data.title,
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "Rating",
                            tint = Color.Yellow
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "${movie.data.vote_average}/10 IMDb",
                            color = Color.White,
                            fontSize = 18.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    LazyRow {
                        items(movie.data.genres.size) { index ->
                            val genre = movie.data.genres[index]
                            Chip(genre.name, Modifier.padding(end = 4.dp))
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Column(horizontalAlignment = Alignment.Start) {
                        Text(
                            text = "Length",
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        val hours = movie.data.runtime / 60
                        val minutes = movie.data.runtime % 60
                        Text(
                            text = "${hours} hr ${minutes} min",
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Description",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = movie.data.overview,
                        color = Color.White,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Director",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            if (movie.data.credits.director.profile_path != null) {
                                Image(
                                    painter = rememberAsyncImagePainter("${BuildConfig.ASSET_URL}${movie.data.credits.director.profile_path}"),
                                    contentDescription = movie.data.credits.director.name,
                                    modifier = Modifier
                                        .size(100.dp)
                                        .clip(RoundedCornerShape(8.dp)),
                                    contentScale = ContentScale.Crop
                                )
                            } else {
                                Box(
                                    modifier = Modifier
                                        .size(100.dp)
                                        .background(Color.Gray)
                                        .clip(RoundedCornerShape(8.dp))
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = movie.data.credits.director.name,
                                color = Color.White,
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center,
                                maxLines = 1
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Cast",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
                item {
                    LazyRow {
                        items(movie.data.credits.cast.size) { index ->
                            val cast = movie.data.credits.cast[index]
                            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(8.dp)) {
                                if (cast.profile_path != null) {
                                    Image(
                                        painter = rememberAsyncImagePainter("${BuildConfig.ASSET_URL}${cast.profile_path}"),
                                        contentDescription = cast.name,
                                        modifier = Modifier
                                            .size(100.dp)
                                            .clip(RoundedCornerShape(8.dp)),
                                        contentScale = ContentScale.Crop
                                    )
                                } else {
                                    Box(
                                        modifier = Modifier
                                            .size(100.dp)
                                            .background(Color.Gray)
                                            .clip(RoundedCornerShape(8.dp))
                                    )
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = cast.name,
                                    color = Color.White,
                                    fontSize = 14.sp,
                                    textAlign = TextAlign.Center,
                                    maxLines = 1,
                                    modifier = Modifier.width(100.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Chip(text: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(Color.White, RoundedCornerShape(16.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            color = Color.Black,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

