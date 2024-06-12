package com.example.cinematch.ui.theme.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.cinematch.R
import coil.request.ImageRequest

data class GenreItem(val genreText: String) // Assuming your data class is defined like this
data class DetailItem(val detailText: String) // Assuming your data class is defined like this
data class CastMember(val name: String, val imageUrl: String)
data class Review(
    val reviewerName: String,
    val reviewText: String,
    val rating: Int
)
@Composable
fun MovieDetailScreen(navController: NavController, modifier: Modifier = Modifier) {
    //Dummy data
    val gridDataGenre = listOf(GenreItem("ACTION"), GenreItem("COMEDY"), GenreItem("DRAMA"))
    val gridDetailText = listOf(DetailItem("Length"), DetailItem("Language"), DetailItem("Rating"))
    val gridDataDetail = listOf(DetailItem("2h 28m"), DetailItem("English"), DetailItem("PG-13"))
    val castList = listOf(
        CastMember("Tom Holland", "https://m.media-amazon.com/images/M/MV5BNzZiNTEyNTItYjNhMS00YjI2LWIwMWQtZmYwYTRlNjMyZTJjXkEyXkFqcGdeQXVyMTExNzQzMDE0._V1_FMjpg_UX1000_.jpg"),
        CastMember("Zendaya", "https://m.media-amazon.com/images/M/MV5BNzZiNTEyNTItYjNhMS00YjI2LWIwMWQtZmYwYTRlNjMyZTJjXkEyXkFqcGdeQXVyMTExNzQzMDE0._V1_FMjpg_UX1000_.jpg"),
        CastMember("Benedict Cumberbatch", "https://m.media-amazon.com/images/M/MV5BNzZiNTEyNTItYjNhMS00YjI2LWIwMWQtZmYwYTRlNjMyZTJjXkEyXkFqcGdeQXVyMTExNzQzMDE0._V1_FMjpg_UX1000_.jpg")
    )
    val reviews = listOf(
        Review("Courtney Henry", "Consequat velit qui adipisicing sunt do rependerit ad laborum tempor ullamco exercitation. Ullamco tempor adipisicing et voluptate duis sit esse aliqua.", 5),
        Review("Darryl Steward", "Enim aliqua aliqua cillum commodo sint excepteur cupidatat dolore magna exercitation do.", 4)
    )

    Column(modifier = modifier
        .background(color = Color(0xff121212))
        .padding(horizontal = 16.dp)) {
        // Image at the top
        Box(modifier = Modifier
            .requiredWidth(375.dp)
            .requiredHeight(300.dp)
            .clip(RectangleShape)) {
            Image(
                painter = painterResource(id = R.drawable.jedi),
                contentDescription = "Top Image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        // Box for the solid color with rounded corners below the image
        Box(
            modifier = Modifier
                .requiredWidth(375.dp)
                .requiredHeight(450.dp)
                .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
                .background(Color(0xff1e1e1e))
                .padding(16.dp)
        ) {
            Column(modifier = Modifier
                .align(Alignment.TopStart)
                .padding(horizontal = 10.dp)) {
                // Title Text
                Text(
                    "Spiderman: No Way Home",
                    color = Color.White,
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Rating Row
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Filled.Star,
                        "Star",
                        tint = Color(0xffE4A70A),
                        modifier = Modifier.requiredSize(12.dp)
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        "9.1/10 IMDb",
                        color = Color.White,
                        style = TextStyle(fontSize = 12.sp)
                    )

                }
                Spacer(Modifier.height(10.dp))
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    content = {
                        items(gridDataGenre) { item ->
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(100.dp))
                                    .background(Color.White)
                                    .padding(horizontal = 12.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    item.genreText,
                                    color = Color(0xff88a4e8),
                                    style = TextStyle(fontSize = 8.sp, fontWeight = FontWeight.Bold)
                                )
                            }
                        }
                    },
                    modifier = Modifier
                        .requiredWidth(210.dp)
                        .requiredHeight(18.dp)
                )
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    content = {
                        items(gridDetailText.size) { index ->
                            Column(
                                horizontalAlignment = Alignment.Start,
                                modifier = Modifier
                                    .padding(horizontal = 3.dp, vertical = 6.dp)
                                    .padding(3.dp)
                            ) {
                                Text(
                                    gridDetailText[index].detailText,
                                    color = Color(0xff88a4e8),
                                    style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold)
                                )
                                Spacer(Modifier.height(5.dp))
                                Text(
                                    gridDataDetail[index].detailText,
                                    color = Color.White,
                                    style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Medium)
                                )
                            }
                        }
                    },
                    modifier = Modifier
                        .requiredWidth(300.dp)
                        .requiredHeight(48.dp)  // Adjust height to accommodate both lines of text
                )
                Spacer(Modifier.height(10.dp))
                Text(
                    text = "Description",
                    color = Color.White,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Black),
                    modifier = Modifier
                        .fillMaxWidth())
                Spacer(Modifier.height(10.dp))
                Text(
                    text = "With Spider-Man's identity now revealed, Peter asks Doctor Strange for help. When a spell goes wrong, dangerous foes from other worlds start to appear, forcing Peter to discover what it truly means to be Spider-Man.",
                    color = Color.White,
                    lineHeight = 1.83.em,
                    style = TextStyle(
                        fontSize = 12.sp),
                    modifier = Modifier
                        .fillMaxWidth())
                Spacer(Modifier.height(20.dp))
                Text(
                    text = "Cast",
                    color = Color(0xff88a4e8),
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Black),
                    modifier = Modifier
                        .fillMaxWidth())
                Spacer(Modifier.height(10.dp))
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    content = {
                        items(castList) { cast ->
                            Column(horizontalAlignment = Alignment.Start, modifier = Modifier.padding(8.dp)) {
                                Image(
                                    painter = // Optional: a placeholder image
                                    rememberAsyncImagePainter(ImageRequest.Builder // Optional: an error image
                                        (LocalContext.current).data(data = cast.imageUrl)
                                        .apply<ImageRequest.Builder>(block = fun ImageRequest.Builder.() {
                                            placeholder(R.drawable.profile_placeholder) // Optional: a placeholder image
                                        }).build()
                                    ),
                                    contentDescription = "${cast.name} photo",
                                    modifier = Modifier
                                        .size(80.dp)
                                )
                                Text(
                                    text = cast.name,
                                    color = Color.White,
                                    style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Medium),
                                    modifier = Modifier.padding(top = 4.dp)
                                )
                            }
                        }
                    },
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                )
            }
        }
        Spacer(Modifier.height(20.dp))
        Text(
            text = "Review",
            color = Color(0xff88a4e8),
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Black),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(10.dp))

        reviews.forEach { review ->
            Box(
                modifier = Modifier
                    .requiredWidth(290.dp)
                    .requiredHeight(120.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .padding(10.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Row(modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.TopCenter)) {
                    Image(
                        painter = painterResource(id = R.drawable.profile_placeholder),
                        contentDescription = "Profile",
                        modifier = Modifier
                            .size(31.dp)
                            .clip(CircleShape)
                    )
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .align(Alignment.Top)
                    ) {
                        Text(
                            text = review.reviewerName,
                            color = Color(0xff333333),
                            style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        )
                        Row {
                            repeat(review.rating) {
                                Icon(
                                    Icons.Filled.Star,
                                    contentDescription = "Star",
                                    tint = Color(0xffE4A70A),
                                    modifier = Modifier.size(13.dp)
                                )
                            }
                        }
                        Text(
                            text = review.reviewText,
                            color = Color(0xff333333),
                            lineHeight = 14.sp,
                            style = TextStyle(fontSize = 10.sp),
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            }
            Spacer(Modifier.height(10.dp))
        }

        Box(modifier = Modifier.align(Alignment.CenterHorizontally)){
            Row(
                modifier = modifier
                    .requiredWidth(width = 290.dp)
                    .clip(shape = RoundedCornerShape(6.7001166343688965.dp))
                    .background(color = Color(0xff95acff))
                    .padding(horizontal = 20.10034942626953.dp,
                        vertical = 13.400233268737793.dp)
            ) {
                Text(
                    text = "Write Review",
                    color = Color(0xff1e1e1e),
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold))

            }
        }


    }
}




@Preview(showBackground = true, widthDp = 360, heightDp = 1500)
@Composable
fun PreviewSReviewScreen() {
    val navController = rememberNavController()
    MovieDetailScreen(navController = navController)
}