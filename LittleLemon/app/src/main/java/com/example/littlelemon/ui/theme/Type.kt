package com.example.littlelemon.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.littlelemon.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    displayLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.markazi_regular)),
        fontSize = 50.sp,
    ),
    displayMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.markazi_regular)),
        fontSize = 30.sp,
    ),
    displaySmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.karla_regular)),
        fontSize = 16.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.karla_regular)),
        fontSize = 20.sp,
        fontWeight = FontWeight(
            weight = 800
        )
    ),
    headlineSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.karla_regular)),
        fontSize = 16.sp,
        fontWeight = FontWeight(
            weight = 300
        )
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.karla_regular)),
        fontSize = 16.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.karla_regular)),
        fontSize = 12.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.karla_regular)),
        fontSize = 12.sp,
        fontWeight = FontWeight(700),
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)