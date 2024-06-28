package com.example.sprapplication.ui.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.common.ui.appBarUI
import com.example.sprapplication.ui.theme.SprApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@Composable
fun testUI() {
    Row {
        Box(modifier = Modifier.size(500.dp)) {
            coil.compose.AsyncImage(
                model = "https://ott-picture.bugaboo.tv/7f205cde-ea13-4a9c-843c-d28529ea438d361875676288512177-l.png",
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f),
                contentScale = ContentScale.Crop
            )
            Text(
                "1",
                color = Color.White,
                fontSize = 90.sp,
                modifier = Modifier.align(Alignment.BottomStart)
            )

        }
//        Box {
//
//            coil.compose.AsyncImage(
//                model = "https://ott-picture.bugaboo.tv/7f205cde-ea13-4a9c-843c-d28529ea438d361875676288512177-l.png",
//                contentDescription = null,
//                modifier =
//                Modifier
//                    .fillMaxWidth()
//                    .aspectRatio(16f / 9f)
//            )
//            Text(
//                "1",
//                color = Color.White,
//                fontSize = 90.sp,
//                modifier = Modifier.align(Alignment.BottomStart)
//            )
//
//        }
    }
}