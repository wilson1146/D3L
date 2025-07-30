package com.example.d2l.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.rizzi.bouquet.ResourceType
import com.rizzi.bouquet.VerticalPDFReader
import com.rizzi.bouquet.rememberVerticalPdfReaderState

@Composable
fun PdfViewerScreen(pdfAssetName: String, onBack: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {

        val context = LocalContext.current
        val resId = remember(pdfAssetName) {
            context.resources.getIdentifier(pdfAssetName, "raw", context.packageName)
        }


        val pdfState = rememberVerticalPdfReaderState(
            resource = ResourceType.Asset(resId),
            isZoomEnable = true
        )
        VerticalPDFReader(
            state = pdfState,
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Gray)
        )

        IconButton(
            onClick = onBack,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(vertical = 15.dp)
        ) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color(0xFF006FFD))
        }
    }
}