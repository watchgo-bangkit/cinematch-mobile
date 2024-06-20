package com.example.cinematch.ui.theme.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.rounded.ThumbDown
import androidx.compose.material.icons.rounded.ThumbUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.cinematch.R
import com.example.cinematch.viewmodel.RecommendationViewModel
import com.alexstyl.swipeablecard.Direction
import com.alexstyl.swipeablecard.ExperimentalSwipeableCardApi
import com.alexstyl.swipeablecard.rememberSwipeableCardState
import com.alexstyl.swipeablecard.swipableCard
import com.example.cinematch.BuildConfig
import com.example.cinematch.data.WatchlistRequest
import com.example.cinematch.viewmodel.WatchlistViewModel
import kotlinx.coroutines.launch

data class Movie(val id: Int, val title: String, val posterUrl: String?)

@OptIn(ExperimentalSwipeableCardApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: RecommendationViewModel = viewModel(),
    watchlistViewModel: WatchlistViewModel = viewModel(),
) {
    val recommendationResponse by viewModel.recommendationResponse.observeAsState(emptyList())
    val loading by viewModel.loading.observeAsState(false)
    val errorMessage by watchlistViewModel.errorMessage.observeAsState("")
    val scope = rememberCoroutineScope()

    var swipeToFetchCount by remember { mutableIntStateOf(0) }
    var swipeCount by remember { mutableIntStateOf(0) }
    var refreshKey by remember { mutableIntStateOf(0) }
    val movies = remember { mutableStateListOf<Movie>() }

    LaunchedEffect(Unit) {
        viewModel.fetchRecommendations()
    }

    LaunchedEffect(swipeToFetchCount) {
        if (swipeToFetchCount == 10) {
            scope.launch {
                viewModel.fetchRecommendationsAgain()
                swipeToFetchCount = 0
            }
        }
    }

    LaunchedEffect(recommendationResponse) {
        scope.launch {
            movies.addAll(recommendationResponse.map {
                Movie(
                    it.movie_id,
                    it.title,
                    it.poster_path
                )
            })
        }
    }

    Box(
        modifier = Modifier
            .background(Color(0xff121212))
            .fillMaxSize()
    ) {
        key(refreshKey) {
            if (loading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = Color.White)
                }
            } else {
                if (movies.isNotEmpty()) {
                    if (errorMessage.isNotEmpty()) {
                        Snackbar(
                            modifier = Modifier.align(Alignment.TopCenter),
                            action = {
                                Button(onClick = { watchlistViewModel.clearErrorMessage() }) {
                                    Text("Dismiss")
                                }
                            }
                        ) { Text(errorMessage) }
                    }
                    Box {
                        val states = movies.reversed().map { it to rememberSwipeableCardState() }

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
                                        posterUrl = movie.posterUrl,
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
                                        swipeToFetchCount++
                                        if (state.swipedDirection.toString() == "Left") {
                                            watchlistViewModel.addWatchlist(
                                                WatchlistRequest(
                                                    movie.id,
                                                    false,
                                                    false
                                                )
                                            )
                                        } else if (state.swipedDirection.toString() == "Up") {
                                            watchlistViewModel.addWatchlist(
                                                WatchlistRequest(
                                                    movie.id,
                                                    true,
                                                    true
                                                )
                                            )
                                        } else {
                                            watchlistViewModel.addWatchlist(
                                                WatchlistRequest(
                                                    movie.id,
                                                    true,
                                                    false
                                                )
                                            )
                                        }
                                        movies.removeAt(0)
                                    }
                                }
                            }
                        }
                        Row(
                            Modifier
                                .align(Alignment.BottomCenter)
                                .padding(bottom = 75.dp, top = 50.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            CircleButton(
                                onClick = {
                                    scope.launch {
                                        val last = states.reversed()
                                            .firstOrNull {
                                                it.second.offset.value == Offset(
                                                    0f,
                                                    0f
                                                )
                                            }?.second
                                        last?.swipe(Direction.Left)
                                    }
                                },
                                icon = Icons.Rounded.ThumbDown,
                                label = "Dislike"
                            )
                            CircleButton(
                                onClick = {
                                    scope.launch {
                                        val last = states.reversed()
                                            .firstOrNull {
                                                it.second.offset.value == Offset(
                                                    0f,
                                                    0f
                                                )
                                            }?.second
                                        last?.swipe(Direction.Up)
                                    }
                                },
                                icon = Icons.Default.CheckCircle,
                                label = "Watched"
                            )
                            CircleButton(
                                onClick = {
                                    scope.launch {
                                        val last = states.reversed()
                                            .firstOrNull {
                                                it.second.offset.value == Offset(
                                                    0f,
                                                    0f
                                                )
                                            }?.second
                                        last?.swipe(Direction.Right)
                                    }
                                },
                                icon = Icons.Rounded.ThumbUp,
                                label = "Watchlist"
                            )
                        }

                    }
                } else {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = "No recommendations available",
                            color = Color.White,
                            fontSize = 18.sp
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
    label: String,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(8.dp)
    ) {
        IconButton(
            modifier = Modifier
                .clip(CircleShape)
                .background(Color.Black)
                .size(56.dp)
                .border(2.dp, Color(0xFF95ACFF), CircleShape),
            onClick = onClick
        ) {
            Icon(
                icon, null, tint = Color(0xFF95ACFF)
            )
        }
        Text(
            text = label,
            color = Color.White,
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
fun MovieCard(
    title: String,
    posterUrl: String?,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF121212))
    ) {
        Box {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = posterUrl?.let { BuildConfig.ASSET_URL + it }
                            ?: R.drawable.profile_placeholder).apply {
                            placeholder(R.drawable.profile_placeholder)
                        }.build()
                ),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
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
