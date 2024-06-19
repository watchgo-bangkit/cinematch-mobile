package com.example.cinematch.ui.theme.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cinematch.BuildConfig
import com.example.cinematch.ui.theme.utils.WatchListCard
import com.example.cinematch.viewmodel.WatchlistViewModel
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.swiperefresh.SwipeRefresh
import kotlinx.coroutines.launch

@Composable
fun WatchListScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: WatchlistViewModel = viewModel(),
) {

    val watchlistData by viewModel.watchlist.observeAsState(emptyList())
    val loading by viewModel.loading.observeAsState(false)
    val errorMessage by viewModel.errorMessage.observeAsState("")
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val swipeRefreshState = remember { SwipeRefreshState(isRefreshing = false) }

    LaunchedEffect(swipeRefreshState) {
        viewModel.fetchWatchlist()
    }

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {
            viewModel.fetchWatchlist()
            swipeRefreshState.isRefreshing = false
        }
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(bottom = 100.dp, top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                "Watchlist",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 16.dp)
            )

            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            if (loading) {
                CircularProgressIndicator(color = Color.White)
            } else if (watchlistData.isEmpty() && !loading) {
                Text(
                    "No watchlist",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 16.dp)
                )
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(watchlistData.size) { index ->
                        val movie = watchlistData[index]
                        WatchListCard(
                            movieId = movie.movie_id,
                            imageUrl = "${BuildConfig.ASSET_URL}${movie.poster_path}",
                            title = movie.title,
                            duration = "${movie.runtime} min",
                            year = movie.released_year,
                            rating = movie.vote_average.toString(),
                            isWatched = movie.is_watched,
                            onWatchedClicked = {
                                viewModel.updateWatchlist(movie.id)
                            },
                            navController = navController
                        )
                    }
                }
            }
            LaunchedEffect(errorMessage) {
                if (errorMessage.isNotEmpty()) {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(errorMessage)
                        viewModel.clearErrorMessage()
                    }
                }
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
