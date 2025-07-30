package com.example.d2l.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.d2l.R
import com.example.d2l.ui.ModuleButton
import com.rizzi.bouquet.ResourceType
import com.rizzi.bouquet.VerticalPDFReader
import com.rizzi.bouquet.rememberVerticalPdfReaderState

@Composable
fun StartHereScreen(navController: NavController) {
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
                .padding(horizontal = 32.dp)
                .padding(vertical = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Start Here", style = MaterialTheme.typography.headlineLarge)
            Spacer(Modifier.height(16.dp))


            ModuleButton(buttonLabel = "Welcome") { navController.navigate("welcome")}
            ModuleButton(buttonLabel = "Syllabus") { navController.navigate("syllabus") }
            ModuleButton(buttonLabel = "Lab Report Instructions") {navController.navigate("lab_report_instructions_screen") }
            ModuleButton(buttonLabel = "Project Instructions") {navController.navigate("project_instruction_screen") }


        }
    }
}

@Composable
fun WelcomeScreen(navController : NavController){



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
            Text("Welcome", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = stringResource(id = R.string.welcome), style = MaterialTheme.typography.bodyLarge)
        }
        Spacer(modifier = Modifier.height(64.dp))
    }

}

@Composable
fun SyllabusScreen(navController : NavController){
    Box(modifier = Modifier.fillMaxSize()){
    val pdfState = rememberVerticalPdfReaderState(
        resource = ResourceType.Asset(R.raw.syl),
        isZoomEnable = true
    )

    VerticalPDFReader(
        state = pdfState,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Gray)
    )

    IconButton(
        onClick = { navController.popBackStack() },
        modifier = Modifier
            .align(Alignment.TopStart)
            .padding(vertical = 15.dp)
    ) {
        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color(0xFF006FFD))
    }
  }
}

@Composable
fun ProjectInstructionsScreen(navController : NavController){
    Box(modifier = Modifier.fillMaxSize()){
        val pdfState = rememberVerticalPdfReaderState(
            resource = ResourceType.Asset(R.raw.projdet),
            isZoomEnable = true
        )

        VerticalPDFReader(
            state = pdfState,
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Gray)
        )

        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(vertical = 15.dp)
        ) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color(0xFF006FFD))
        }
    }
}

@Composable
fun LabReportInstructionsScreen(navController : NavController){
    Box(modifier = Modifier.fillMaxSize()){
        val pdfState = rememberVerticalPdfReaderState(
            resource = ResourceType.Asset(R.raw.labr),
            isZoomEnable = true
        )

        VerticalPDFReader(
            state = pdfState,
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Gray)
        )

        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(vertical = 15.dp)
        ) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color(0xFF006FFD))
        }
    }
}

