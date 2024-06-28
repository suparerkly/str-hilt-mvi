package com.example.common.ui

import android.os.Bundle
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.layout.ContentScale.Companion.FillBounds
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.common.viewmodel.CommonViewModel
import com.example.common.viewmodel.HomeViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LayoutBugaboo(navController: NavController) {
    val viewModel = hiltViewModel<HomeViewModel>()
    val commonViewModel = hiltViewModel<CommonViewModel>()
    val bannerUiState = viewModel.bannerUiState.collectAsState()
    val vertical1UiState = viewModel.vertical1UiState.collectAsState()
    val horizontal1UiState = viewModel.horizontal1UiState.collectAsState()
    val firebaseUiState by commonViewModel.topAppBarUiState.collectAsState()

    var width by remember { mutableIntStateOf(0) }

    if (firebaseUiState != null) {
        width = firebaseUiState.data?.banner?.width ?: 0
    }

    val pagerState =
        rememberPagerState(pageCount = {
            firebaseUiState.data?.banner?.img?.size ?: 0
        })

    val list = firebaseUiState.data?.banner?.ratio?.split(":")
    var ratioImg = list?.let {
        it[0].toFloat() / list[1].toFloat()
    }

    Column(
        modifier =
        Modifier
            .padding(horizontal = 8.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        headerBugabooTv()
        Spacer(modifier = Modifier.width(8.dp))
        HorizontalPager(state = pagerState) { page ->
            Box(modifier = Modifier.clickable {
                navController.navigate("secondscreen/${bannerUiState.value.data?.get(page)?.id}")
            }) {
                coil.compose.AsyncImage(
                    model = firebaseUiState.data?.banner?.img?.get(page),
                    contentDescription = null,
                    contentScale = if (firebaseUiState.data?.banner?.scale == "crop") Crop else FillBounds,
                    modifier =
                    Modifier
                        .aspectRatio(ratioImg ?: 1f)
                        .width(100.dp),
                )
                Column(
                    modifier = Modifier.align(Alignment.BottomCenter),
                ) {
                    Row {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text(text = bannerUiState.value.data?.get(page)?.title ?: "")
                            Row {
                                for (i in 0..5) {
                                    Icon(
                                        imageVector = Icons.Outlined.Star,
                                        contentDescription = null,
                                        tint = Color.Red,
                                    )
                                }
                                Text(
                                    text = bannerUiState.value.data?.get(page)?.year ?: "",
                                    modifier = Modifier.padding(top = 3.dp, start = 3.dp),
                                    color = Color.White
                                )
                                Surface(
                                    modifier = Modifier.padding(start = 5.dp),
                                    shape = RoundedCornerShape(corner = CornerSize(3.dp)),
                                    border = BorderStroke(width = 2.dp, color = Color.White),
                                    color = Color.Transparent, // This is what you're missing
                                ) {
                                    Text(
                                        modifier = Modifier.padding(start = 6.dp, end = 6.dp),
                                        text = bannerUiState.value.data?.get(page)?.rate ?: "",
                                        color = Color.White,
                                    )
                                }
                                Text(
                                    text = "18 ตอน",
                                    modifier = Modifier.padding(bottom = 3.dp, start = 5.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
        Row(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) Color.Red else Color.LightGray
                Box(
                    modifier =
                    Modifier
                        .padding(8.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(8.dp),
                )
            }
        }
        Column {
            Text(vertical1UiState.value.data?.title ?: "", color = Color.White)
            Spacer(modifier = Modifier.height(10.dp))
            LazyRow(modifier = Modifier.clickable {
                navController.navigate("detailScreen")
            }) {
                items(vertical1UiState.value.data?.dramas?.size ?: 0) { index ->
                    coil.compose.AsyncImage(
                        model = vertical1UiState.value.data?.dramas?.get(index)?.image,
                        contentDescription = null,
                        modifier =
                        Modifier
                            .widthIn(50.dp, 200.dp),
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(horizontal1UiState.value.data?.title ?: "", color = Color.White)
            LazyRow(modifier = Modifier.padding(top = 10.dp)) {
                items(horizontal1UiState.value.data?.dramas?.size ?: 0) { index ->
//                    Horizontal1(
//                        horizontal1UiState.value.data?.dramas?.get(index)?.image ?: "",
//                        Modifier.fillParentMaxWidth(0.7f)
//                    )
                    coil.compose.AsyncImage(
                        model = horizontal1UiState.value.data?.dramas?.get(index)?.image,
                        contentDescription = null,
                        modifier = Modifier.fillParentMaxWidth(0.7f)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                }
            }
        }
    }
}