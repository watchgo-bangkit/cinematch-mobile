package com.example.cinematch.ui.theme.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.ThumbUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cinematch.R
import com.alexstyl.swipeablecard.Direction
import com.alexstyl.swipeablecard.ExperimentalSwipeableCardApi
import com.alexstyl.swipeablecard.rememberSwipeableCardState
import com.alexstyl.swipeablecard.swipableCard
import kotlinx.coroutines.launch

data class Movie(val id: Int, val title: String, val poster: Int)

suspend fun fetchMoreMovies(onMoviesFetched: (List<Movie>) -> Unit) {
    kotlinx.coroutines.delay(2000)
    val newMovies = listOf(
        Movie(4, "Movie 4", R.drawable.jedi),
        Movie(5, "Movie 5", R.drawable.jedi),
        Movie(6, "Movie 6", R.drawable.jedi),
    )
    onMoviesFetched(newMovies)
}

@OptIn(ExperimentalSwipeableCardApi::class)
@Composable
fun HomeScreen(navController: NavController, modifier: Modifier = Modifier) {
    var movies by remember {
        mutableStateOf(
            listOf(
                Movie(1, "Movie 1", R.drawable.jedi),
                Movie(2, "Movie 2", R.drawable.jedi),
                Movie(3, "Movie 3", R.drawable.jedi)
            )
        )
    }
    val scope = rememberCoroutineScope()

    var refreshKey by remember { mutableIntStateOf(0) }
    var swipeCount by remember { mutableIntStateOf(0) }

    LaunchedEffect(refreshKey) {
        swipeCount = 0
        fetchMoreMovies { newMovies ->
            movies = newMovies
        }
    }

    Box(
        modifier = Modifier
            .background(Color(0xff121212))
            .fillMaxSize()
    ) {
        if (swipeCount >= 3) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = { refreshKey++ },
                    modifier = Modifier.align(Alignment.Center),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF95ACFF))
                ) {
                    Text("Refresh")
                }
            }
        }
        key(refreshKey) {
            if (swipeCount < 3) {
                Box {
                    val states = movies.reversed()
                        .map { it to rememberSwipeableCardState() }
                    Box(
                        Modifier
                            .padding(horizontal = 24.dp)
                            .fillMaxSize()
                            .aspectRatio(3f / 4f)
                    ) {
                        states.forEach { (movie, state) ->
                            if (state.swipedDirection == null) {
                                MovieCard(
                                    title = movie.title,
                                    posterResId = movie.poster,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .swipableCard(state = state,
                                            blockedDirections = listOf(Direction.Down),
                                            onSwiped = {
                                            },
                                            onSwipeCancel = {
                                                Log.d("Swipeable-Card", "Cancelled swipe")
                                            }),
                                )

                            }
                            LaunchedEffect(movie, state.swipedDirection) {
                                if (state.swipedDirection != null) {
                                    swipeCount++
                                }
                                //  TODO : FETCH API BERDASARKAN SWIPE DIRECTION
                            }
                        }
                    }
                    Row(
                        Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 100.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        CircleButton(
                            onClick = {
                                scope.launch {
                                    val last = states.reversed()
                                        .firstOrNull {
                                            it.second.offset.value == Offset(0f, 0f)
                                        }?.second
                                    last?.swipe(Direction.Left)
                                }
                            },
                            icon = Icons.Rounded.Close
                        )
                        CircleButton(
                            onClick = {
                                scope.launch {
                                    val last = states.reversed()
                                        .firstOrNull {
                                            it.second.offset.value == Offset(0f, 0f)
                                        }?.second

                                    last?.swipe(Direction.Up)
                                }
                            },
                            icon = Icons.Rounded.Favorite
                        )
                        CircleButton(
                            onClick = {
                                scope.launch {
                                    val last = states.reversed()
                                        .firstOrNull {
                                            it.second.offset.value == Offset(0f, 0f)
                                        }?.second

                                    last?.swipe(Direction.Right)
                                }
                            },
                            icon = Icons.Rounded.ThumbUp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CircleButton(
    onClick: () -> Unit,
    icon: ImageVector,
) {
    IconButton(
        modifier = Modifier
            .clip(CircleShape)
            .background(Color.Black)
            .size(56.dp)
            .border(2.dp, Color(0xFF95ACFF), CircleShape), onClick = onClick
    ) {
        Icon(
            icon, null, tint = Color(0xFF95ACFF)
        )
    }
}

@Composable
fun MovieCard(
    title: String,
    posterResId: Int,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF121212))
    ) {
        Box {
            Image(
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(posterResId),
                contentDescription = null
            )
            Scrim(Modifier.align(Alignment.BottomCenter))
            Column(Modifier.align(Alignment.BottomStart)) {
                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}

@Composable
fun Scrim(modifier: Modifier = Modifier) {
    Box(
        modifier
            .background(Brush.verticalGradient(listOf(Color.Transparent, Color.Black)))
            .height(180.dp)
            .fillMaxWidth()
    )
}


