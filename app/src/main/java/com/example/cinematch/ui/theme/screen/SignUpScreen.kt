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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cinematch.R
import com.example.cinematch.ui.theme.utils.CustomButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import java.util.regex.Pattern


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavController, modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }

    val emailPattern = Pattern.compile(
        "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    )

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xff121212))
            .padding(16.dp)
    ) {
        Text(
            text = "Sign Up",
            color = Color(0xfff3f3f3),
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 32.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = { Text(text = "Email", color = Color.Gray) },
            leadingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.email),
                    contentDescription = "Email",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(24.dp)
                )
            },
            textStyle = TextStyle(color = Color.White),
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
                cursorColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
                .clip(RoundedCornerShape(30.dp))
                .border(BorderStroke(1.dp, Color(0xff95acff)), shape = RoundedCornerShape(30.dp))
                .background(Color.Transparent)
                .padding(horizontal = 16.dp, vertical = 6.dp)
        )

        if (emailError) {
            Text(
                text = "Invalid email address",
                color = Color.Red,
                style = TextStyle(fontSize = 12.sp),
                modifier = Modifier
                    .padding(bottom = 10.dp, start = 10.dp)
                    .align(Alignment.Start)
            )
        }

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            placeholder = { Text(text = "Username", color = Color.Gray) },
            leadingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = "Username",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(24.dp)
                )
            },
            textStyle = TextStyle(color = Color.White),
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
                cursorColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
                .clip(RoundedCornerShape(30.dp))
                .border(BorderStroke(1.dp, Color(0xff95acff)), shape = RoundedCornerShape(30.dp))
                .background(Color.Transparent)
                .padding(horizontal = 16.dp, vertical = 6.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text(text = "Password", color = Color.Gray) },
            leadingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.lock),
                    contentDescription = "Password",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(24.dp)
                )
            },
            trailingIcon = {
                val image = if (passwordVisible) {
                    Icons.Filled.Visibility
                } else {
                    Icons.Filled.VisibilityOff
                }
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, contentDescription = "Toggle password visibility")
                }
            },
            textStyle = TextStyle(color = Color.White),
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
                cursorColor = Color.White
            ),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
                .clip(RoundedCornerShape(30.dp))
                .border(BorderStroke(1.dp, Color(0xff95acff)), shape = RoundedCornerShape(30.dp))
                .background(Color.Transparent)
                .padding(horizontal = 16.dp, vertical = 6.dp)
        )

        if (passwordError) {
            Text(
                text = "Password must be at least 5 characters",
                color = Color.Red,
                style = TextStyle(fontSize = 12.sp),
                modifier = Modifier
                    .padding(bottom = 20.dp, start = 10.dp)
                    .align(Alignment.Start) // Align to the start (left)
            )
        }

        CustomButton(
            text = "Create an Account",
            textColor = Color(0xff121212),
            backgroundColor = Color(0xff95acff),
            onClick = {
                emailError = !emailPattern.matcher(email).matches()
                passwordError = password.length < 5
                if (!emailError && !passwordError) {
                    // Handle successful login
                } },
            modifier = Modifier.fillMaxWidth().padding(top = 10.dp)
        )

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        ) {
            Text(
                text = "Already have an account?",
                color = Color.White,
                textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 13.sp),
                modifier = Modifier.padding(end = 4.dp)
            )
            Text(
                text = "Login",
                color = Color(0xff95acff),
                textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 13.sp),
                modifier = Modifier.clickable { navController.navigate("login") }
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
fun PreviewSignUpScreen() {
    val navController = rememberNavController()
    SignUpScreen(navController = navController)
}
