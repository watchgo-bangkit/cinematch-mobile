package com.example.cinematch.ui.theme.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.cinematch.R
import com.example.cinematch.api.TokenManager
import com.example.cinematch.viewmodel.AuthenticationViewModel
import java.util.Locale

@Composable
fun ProfileScreen(navController: NavController, modifier: Modifier = Modifier) {
    val context = navController.context
    val tokenManager = remember { TokenManager(context) }
    val userProfile by remember { mutableStateOf(tokenManager.getUserProfile()) }
    var showLogoutDialog by remember { mutableStateOf(false) }

    val authViewModel: AuthenticationViewModel = viewModel()

    if (userProfile != null) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .background(Color(0xff121212))
                .padding(16.dp)
        ) {
            Image(
                contentScale = ContentScale.Crop,
                modifier = Modifier.width(180.dp).clip(RoundedCornerShape(20.dp)),
                painter = painterResource(R.drawable.profile_placeholder),
                contentDescription = null
            )
            Text(
                text = "Profile",
                color = Color(0xfff3f3f3),
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(bottom = 32.dp, top = 20.dp)
            )

            fun capitalizeFirstLetter(input: String): String {
                if (input.isEmpty()) {
                    return input // Return empty string if input is empty
                }

                return input.substring(0, 1).uppercase() + input.substring(1).lowercase()
            }

            ProfileItem(label = "Name", value = userProfile!!.data.user.name)
            ProfileItem(label = "Email", value = userProfile!!.data.user.email)
            ProfileItem(label = "Age", value = userProfile!!.data.user.age.toString())
            ProfileItem(label = "Gender", value = capitalizeFirstLetter(userProfile!!.data.user.gender))

            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = {
                    showLogoutDialog = true
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xff95acff))
            ) {
                Text(
                    text = "Logout",
                    color = Color(0xff121212),
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
                )
            }
            if (showLogoutDialog) {
                AlertDialog(
                    onDismissRequest = {
                        showLogoutDialog = false
                    },
                    title = {
                        Text(text = "Logout")
                    },
                    text = {
                        Text(text = "Are you sure you want to logout?")
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                authViewModel.logout()
                                navController.navigate("login") {
                                    popUpTo(0) { inclusive = true }
                                }
                                showLogoutDialog = false
                            }
                        ) {
                            Text(text = "Logout")
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = {
                                showLogoutDialog = false
                            }
                        ) {
                            Text(text = "Cancel")
                        }
                    }
                )
            }
        }
    } else {
        LaunchedEffect(Unit) {
            navController.navigate("login") {
                popUpTo(0) { inclusive = true }
            }
        }
    }
}

@Composable
fun ProfileItem(label: String, value: String) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = label,
            color = Color(0xff95acff),
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold) ,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = value,
            color = Color.White,
            style = TextStyle(fontSize = 18.sp)
        )
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
fun PreviewProfileScreen() {
    val navController = rememberNavController()
    ProfileScreen(navController = navController)
}
