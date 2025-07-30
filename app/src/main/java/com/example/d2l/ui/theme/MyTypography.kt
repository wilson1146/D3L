package com.example.d2l.ui.theme


import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.d2l.R

val MyAppFontFamily = FontFamily(
    Font(R.font.crimsontext_bold, weight = FontWeight.Bold),
    Font(R.font.inter_24pt_regular, weight = FontWeight.Normal),
    Font(R.font.inter_24pt_medium, weight = FontWeight.Medium),
    Font(R.font.inter_24pt_bold, weight = FontWeight.SemiBold)

)

val MyTypography = Typography(
    headlineLarge = TextStyle(
        fontFamily = MyAppFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 64.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = MyAppFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    labelLarge = TextStyle(
        fontFamily = MyAppFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),


)