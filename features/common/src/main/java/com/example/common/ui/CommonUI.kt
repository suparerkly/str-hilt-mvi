package com.example.common.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.common.R
import com.example.common.state.AppBarUiState
import com.example.common.viewmodel.CommonViewModel
import com.example.common.viewmodel.HomeViewModel
import com.example.data.common.api.APIService
import com.example.data.home.model.LogoItem
import javax.inject.Inject


@Composable
fun appBarUI(navController: NavController) {
    loadingAnimation(navController)
}

@Composable
fun headerBugabooTv() {
    val viewModel = hiltViewModel<HomeViewModel>()

    val homeBannerState = viewModel.homeBannerUiState.collectAsState()

    var logo by remember { mutableStateOf(listOf<LogoItem>()) }

    logo = homeBannerState.value.data?.data?.logo ?: listOf()

    Row(
        modifier =
        Modifier
            .padding(top = 30.dp)
            .fillMaxWidth(),
    ) {
        logo.forEach {
            if (it.type == "square") {
                imageUI(image = it.image ?: "", sizeImg = 30)
            } else if (it.type == "rectangle") {
                imageSvgUI(image = it.image ?: "")
            }
        }
        buttonLogin()
    }
}

@Composable
fun loadingAnimation(navController: NavController) {
    val viewModel = hiltViewModel<HomeViewModel>()
    val loadingUiState = viewModel.loadingUiState.collectAsState()

    var loading by remember { mutableStateOf(false) }

    if (loading != null) {
        loading = loadingUiState.value
    }

    if (loading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            lottieCloseExample(
                modifier = Modifier
                    .size(200.dp)
            )
        }
    } else {
        LayoutBugaboo(navController)
    }
}

@Composable
fun lottieCloseExample(modifier: Modifier) {
    val preloaderLottieComposition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.animation_loading)
    )

    val preloaderProgress by animateLottieCompositionAsState(
        preloaderLottieComposition,
        iterations = 1,
        isPlaying = true,
    )

    LottieAnimation(
        composition = preloaderLottieComposition,
        progress = preloaderProgress,
        modifier = modifier,
        alignment = Alignment.Center
    )
}
