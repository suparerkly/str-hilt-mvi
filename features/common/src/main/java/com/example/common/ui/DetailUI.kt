package com.example.common.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ScreenDetail(bannerId: Int?) {
    Column {
        Box(modifier = Modifier.size(500.dp)) {
            coil.compose.AsyncImage(
                model = "https://ott-picture.bugaboo.tv/7f205cde-ea13-4a9c-843c-d28529ea438d361875676288512177-l.png",
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f),
                contentScale = ContentScale.Crop
            )
        }
        Text("BugabooTV ข่าว ${bannerId.toString()}")
    }
}

@Composable
fun DetailScreen() {
    Column {
        Box(modifier = Modifier.size(500.dp)) {
            coil.compose.AsyncImage(
                model = "https://ott-picture.bugaboo.tv/7f205cde-ea13-4a9c-843c-d28529ea438d361875676288512177-l.png",
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f),
                contentScale = ContentScale.Crop
            )
        }
        Text("BugabooTV ซีรีย์")
    }
}