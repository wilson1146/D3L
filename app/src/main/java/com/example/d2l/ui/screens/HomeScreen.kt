package com.example.d2l.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.d2l.R
import com.example.d2l.ui.theme.MyAppFontFamily

@Composable
fun HomeScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "D2L Logo",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(20.dp)
                .size(48.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp)
                .padding(vertical = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Home",
                fontFamily = MyAppFontFamily,
                fontSize = (64.sp),
                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(bottom = 36.dp)
            )

            HomeNavButton(text = "Start Here") {

                navController.navigate("start_here")
            }

            Spacer(modifier = Modifier.height(16.dp))

            HomeNavButton(text = "Learning Modules") {
                navController.navigate("learning_modules")
            }
        }
    }
}


@Composable
fun HomeNavButton(text: String, onClick: () -> Unit) {
    Button(

        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEAF2FF)),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = text,
                color = Color.Black,
                fontFamily = MyAppFontFamily,
                fontWeight = FontWeight.Normal
            )
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Go",
                tint = Color.Black)
        }
    }
}