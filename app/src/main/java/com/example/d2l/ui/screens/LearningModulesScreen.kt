package com.example.d2l.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.d2l.R
import com.example.d2l.ui.ModuleButton

@Composable
fun LearningModulesScreen(navController: NavController) {
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
                .padding(top = 60.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
        ) {
            Text(
                text = "Learning Modules",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 16.dp).align(Alignment.CenterHorizontally),
                fontSize = 36.sp

            )

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(13) { index ->
                    val week = index + 1
                    ModuleButton(buttonLabel = "Week $week") {
                        navController.navigate("week/$week")
                    }
                }
            }
        }
    }
}