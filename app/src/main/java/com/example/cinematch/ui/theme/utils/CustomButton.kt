package com.example.cinematch.ui.theme.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.*
import androidx.compose.material3.*
import androidx.compose.ui.*
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.*
import androidx.compose.ui.unit.*

@Composable
fun CustomButton(
    text: String,
    textColor: Color,
    backgroundColor: Color,
    borderColor: Color? = null, // Optional border color
    onClick: () -> Unit // What should happen when the button is clicked
) {
    val buttonModifier = Modifier
        .requiredWidth(width = 328.dp)
        .requiredHeight(height = 52.dp)
        .clip(shape = RoundedCornerShape(10.dp))
        .background(color = backgroundColor)
        .padding(horizontal = 24.dp, vertical = 8.dp)
        .clickable(onClick = onClick) // Handling click

    if (borderColor != null) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = buttonModifier.border(BorderStroke(1.dp, borderColor), shape = RoundedCornerShape(10.dp))
        ) {
            Text(
                text = text,
                color = textColor,
                textAlign = TextAlign.Center,
                lineHeight = 1.43.em,
                style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold)
            )
        }
    } else {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = buttonModifier
        ) {
            Text(
                text = text,
                color = textColor,
                textAlign = TextAlign.Center,
                lineHeight = 1.43.em,
                style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold)
            )
        }
    }
}
