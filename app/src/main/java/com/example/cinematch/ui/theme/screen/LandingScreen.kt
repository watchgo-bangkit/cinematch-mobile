package com.example.cinematch.ui.theme.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cinematch.R
import com.example.cinematch.ui.theme.utils.CustomButton

@Composable
fun LandingScreen(navController: NavController, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 360.dp)
            .requiredHeight(height = 800.dp)
            .clip(shape = RoundedCornerShape(30.dp))
            .background(color = Color(0xff121212))
    ) {
        Box(
            modifier = Modifier
                .requiredWidth(width = 360.dp)
                .requiredHeight(height = 800.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.jedi),
                contentDescription = "jedi",
                modifier = Modifier
                    .requiredWidth(width = 360.dp)
                    .requiredHeight(height = 800.dp)
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 0.dp, y = 503.dp)
                .requiredHeight(height = 297.dp)
                .clip(shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
                .background(color = Color(0xff121212).copy(alpha = 0.9f))
                .padding(start = 16.dp, end = 28.dp, top = 29.dp, bottom = 29.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Watch movies anytime anywhere",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    lineHeight = 1.em,
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                )
                Text(
                    text = "Explore a vast collection of blockbuster movies, timeless classics, and the latest releases.",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    lineHeight = 1.54.em,
                    style = TextStyle(fontSize = 13.sp),
                    modifier = Modifier.requiredWidth(width = 320.dp)
                )
            }
            CustomButton(
                text = "Login",
                textColor = Color(0xff121212),
                backgroundColor = Color(0xff95acff),
                onClick = { navController.navigate("login") }
            )

            CustomButton(
                text = "Sign Up",
                textColor = Color(0xff95acff),
                backgroundColor = Color.White,
                borderColor = Color(0xff95acff),
                onClick = { navController.navigate("signup") }
            )
        }
        Text(
            textAlign = TextAlign.Center,
            lineHeight = 1.sp,
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color(0xfff3f3f3), fontSize = 30.sp)) {
                    append("CINE")
                }
                withStyle(style = SpanStyle(color = Color(0xff95acff), fontSize = 30.sp)) {
                    append("MATCH")
                }
            },
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 79.dp, y = 86.dp)
                .requiredWidth(width = 204.dp)
                .requiredHeight(height = 35.dp)
        )
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
fun PreviewLandingScreen() {
    val navController = rememberNavController() // Only for preview purposes
    LandingScreen(navController = navController)
}

