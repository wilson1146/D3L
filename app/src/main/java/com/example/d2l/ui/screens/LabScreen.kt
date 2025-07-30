package com.example.d2l.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.d2l.R

fun getLabTextResourceId(weekNumber: Int): Int {
    return when (weekNumber) {
        1 -> R.string.l1
        2 -> R.string.l2
        3 -> R.string.l3
        4 -> R.string.l4
        5 -> R.string.l5
        6 -> R.string.l6
        7 -> R.string.l7
        8 -> R.string.l8
        9 -> R.string.l9
        10 -> R.string.l10
        11 -> R.string.l11
        12 -> R.string.l12
        else -> R.string.l13 // fallback
    }
}


@Composable
fun LabScreen(weekNumber: Int, navController: NavController) {
    val context = LocalContext.current
    val labText = stringResource(id = getLabTextResourceId(weekNumber))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .padding(vertical = 20.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color(0xFF006FFD)
            )
        }


        Column(modifier = Modifier.padding(horizontal = 24.dp)) {
            Text("Lab for Week $weekNumber", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Text(labText, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
