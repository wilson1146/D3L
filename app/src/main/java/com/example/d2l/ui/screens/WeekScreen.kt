package com.example.d2l.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.d2l.R
import com.example.d2l.ui.ModuleButton

@Composable
fun WeekScreen(weekNumber: Int, navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {

        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(vertical = 20.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color(0xFF006FFD)
            )
        }


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
                .padding(horizontal = 32.dp, vertical = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Week $weekNumber",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(32.dp))

            ModuleButton(buttonLabel = "Slides", subtext = "PDF") {
                navController.navigate("slides_pdf/week${weekNumber}")
            }

            ModuleButton(buttonLabel = "Lab", subtext = "Text") {
                navController.navigate("lab/$weekNumber")
            }
        }
    }
}


