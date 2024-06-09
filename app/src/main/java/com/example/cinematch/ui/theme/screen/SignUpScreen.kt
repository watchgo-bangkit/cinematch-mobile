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
import androidx.compose.runtime.*
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue


@Composable
fun SignUpScreen(navController: NavController, modifier: Modifier = Modifier) {
    val radioOptions = listOf("Female", "Male", "Not Specified")
    var selectedGender by remember { mutableStateOf("") }

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

        Column( // Use Column for vertical stacking
            verticalArrangement = Arrangement.SpaceBetween, // Adjust spacing as needed
            modifier = Modifier
                .requiredWidth(329.dp)
        ) {
            Text( // Text label above radio buttons
                text = "Gender:",
                color = Color.White
            )
            Row( // Row to hold multiple Columns
                horizontalArrangement = Arrangement.spacedBy(16.dp), // Adjust spacing between Columns
                modifier = Modifier.padding(horizontal = 4.dp)
            ) {
                radioOptions.forEach { option ->
                    Column( // Individual Column for each option + radio button
                        modifier = Modifier.weight(1f) // Share available space equally
                    ) {
                        RadioButton( // Radio button in its Column
                            selected = selectedGender == option,
                            onClick = { selectedGender = option },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Color(0xff95acff), // Set selected color to white
                                unselectedColor = Color.White // Optional: adjust unselected color
                            )
                        )
                        Text( // Option text below radio button
                            text = option,
                            color = Color.White
                        )
                    }
                }
            }
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