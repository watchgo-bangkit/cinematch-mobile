package com.example.cinematch.ui.theme.utils

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.cinematch.R

@Composable
fun WatchListCard(
    modifier: Modifier = Modifier,
    movieId: Int,
    imageUrl: String,
    title: String,
    duration: String,
    year: String,
    rating: String,
    isWatched: Boolean,
    onWatchedClicked: () -> Unit,
    navController: NavController
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(8.dp)
            .border(BorderStroke(0.5.dp, Color.Black), RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFF1C1C1C))
            .clickable {
                navController.navigate("movieDetail/$movieId")
            }
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current).data(data = imageUrl).apply {
                    placeholder(R.drawable.profile_placeholder)
                }.build()
            ),
            contentDescription = "Cover Image for $title",
            modifier = Modifier
                .width(60.dp)
                .height(100.dp)
                .clip(RoundedCornerShape(4.dp))
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                color = Color.White,
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(4.dp))
            InfoTag(duration, Icons.Filled.Timer)
            Spacer(modifier = Modifier.height(4.dp))
            InfoTag(year, Icons.Filled.CalendarToday)
            Spacer(modifier = Modifier.height(4.dp))
            InfoTag(rating, Icons.Filled.Star)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(4.dp))
                    .background(color = if (isWatched) Color.Gray else Color(0xff95acff))
                    .clickable(
                        enabled = !isWatched,
                        onClick = onWatchedClicked
                    )
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (isWatched) "Watched" else "Add to Watched",
                    color = Color(0xff121212),
                    textAlign = TextAlign.Center,
                    style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold)
                )
            }
        }
    }
}

@Composable
fun InfoTag(text: String, icon: ImageVector, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(16.dp)
        )
        Spacer(Modifier.width(4.dp))
        Text(
            text = text,
            color = Color.White,
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        )
    }
}
