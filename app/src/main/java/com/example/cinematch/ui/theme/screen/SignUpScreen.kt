package com.example.cinematch.ui.theme.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.*
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.*


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.style.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cinematch.R
import com.example.cinematch.ui.theme.utils.CustomButton


@Composable
fun SignUpScreen(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(30.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .requiredWidth(360.dp)
            .requiredHeight(2000.dp)
            .clip(RoundedCornerShape(30.dp))
            .background(Color(0xff121212))
            .padding(vertical = 287.dp)
    ) {
        Text(
            text = "Sign Up",
            color = Color(0xfff3f3f3),
            textAlign = TextAlign.Center,
            lineHeight = 0.83.em,
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .requiredWidth(329.dp)
                .requiredHeight(52.dp)
                .clip(RoundedCornerShape(30.dp))
                .border(BorderStroke(1.dp, Color(0xff95acff)),
                    shape = RoundedCornerShape(30.dp))
                .padding(horizontal = 24.dp, vertical = 8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.email),
                contentDescription = "Email",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(30.dp)
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .requiredWidth(329.dp)
                .requiredHeight(52.dp)
                .clip(RoundedCornerShape(30.dp))
                .border(BorderStroke(1.dp, Color(0xff95acff)),
                    shape = RoundedCornerShape(30.dp))
                .padding(horizontal = 24.dp, vertical = 8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.user),
                contentDescription = "Username",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(30.dp)
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .requiredWidth(329.dp)
                .requiredHeight(52.dp)
                .clip(RoundedCornerShape(30.dp))
                .border(BorderStroke(1.dp, Color(0xff95acff)),
                    shape = RoundedCornerShape(30.dp))
                .padding(horizontal = 24.dp, vertical = 8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.lock),
                contentDescription = "Lock",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(30.dp)
            )
        }

        Row(
            horizontalArrangement = Arrangement.Center,  // Ensure this is centering content
        ) {
            Text(
                text = "Already have an account?",
                color = Color.White,
                textAlign = TextAlign.Center,
                lineHeight = 1.54.em,
                style = TextStyle(fontSize = 13.sp)
            )
            Text(
                text = "Login",
                color = Color(0xff95acff),
                textAlign = TextAlign.Center,
                lineHeight = 1.54.em,
                style = TextStyle(fontSize = 13.sp)
            )
        }

        CustomButton(
            text = "Create an Account",
            textColor = Color(0xff121212),
            backgroundColor = Color(0xff95acff),
            onClick = {  }
        )

    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
fun PreviewSignUpScreen() {
    val navController = rememberNavController()
    SignUpScreen(navController = navController)
}
