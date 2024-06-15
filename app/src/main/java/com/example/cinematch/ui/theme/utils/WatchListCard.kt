package com.example.cinematch.ui.theme.utils

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cinematch.R

@Composable
fun WatchListCard(
    modifier: Modifier = Modifier,
    imageId: Int,
    title: String,
    genres: List<String>,
    duration: String,
    year: String,
    rating: String
) {
    Box(
        modifier = modifier
            .size(width = 302.dp, height = 127.dp)
            .clip(RoundedCornerShape(4.dp))
            .border(BorderStroke(1.5.dp, Color.White), RoundedCornerShape(4.dp))
    ) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = "Cover Image for $title",
            modifier = Modifier
                .size(width = 105.dp, height = 127.dp)
                .clip(RoundedCornerShape(4.dp))
        )
        Column(modifier = Modifier
            .padding(start = 120.dp, top = 10.dp)
            .align(Alignment.TopStart)
        ) {
            Text(
                text = title,
                color = Color.White,
                style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium)
            )
            Spacer(Modifier.height(10.dp))
            Row {
                genres.forEach { genre ->
                    GenreTag(genre)
                    Spacer(Modifier.width(5.dp))
                }
            }
            InfoTag(duration, Icons.Filled.Timer, Modifier.padding(top = 8.dp))
            Row {
                InfoTag(year, Icons.Filled.CalendarToday, Modifier.padding(top = 4.dp))
                Spacer(Modifier.width(5.dp))
                InfoTag(rating, Icons.Filled.Star, Modifier.padding(top = 4.dp))
            }
        }
    }
}

@Composable
fun GenreTag(text: String) {
    Text(
        text = text,
        color = Color(0xff88a4e8),
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(Color.White)
            .padding(horizontal = 4.dp, vertical = 2.dp),
        style = TextStyle(fontSize = 13.sp)
    )
}

@Composable
fun InfoTag(text: String, icon: ImageVector, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.White
        )
        Spacer(Modifier.width(4.dp))
        Text(
            text = text,
            color = Color.White,
            style = TextStyle(
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium
            )
        )
    }
}

@Preview
@Composable
private fun WatchListCardPreview() {
    WatchListCard(
        imageId = R.drawable.jedi,
        title = "John Wick: Chapter 4",
        genres = listOf("Action", "Thriller", "Crime"),
        duration = "2 hr 50 min",
        year = "2023",
        rating = "8.5"
    )
}
