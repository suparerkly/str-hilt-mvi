package com.example.common.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.state.BannerUiState
import com.example.common.state.HomeBannerUiState
import com.example.common.state.Horizontal1UiState
import com.example.common.state.Vertical1UiState
import com.example.data.appbar.repository.HomeBannerRepository
import com.example.data.common.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeBannerRepository: HomeBannerRepository
) : ViewModel() {

    private val _homeBannerUiState = MutableStateFlow(HomeBannerUiState())
    val homeBannerUiState: StateFlow<HomeBannerUiState> get() = _homeBannerUiState

    private val _loadingUiState = MutableStateFlow(false)
    val loadingUiState: StateFlow<Boolean> get() = _loadingUiState

    private val _bannerUiState = MutableStateFlow(BannerUiState())
    val bannerUiState: StateFlow<BannerUiState> get() = _bannerUiState

    private val _vertical1UiState = MutableStateFlow(Vertical1UiState())
    val vertical1UiState: StateFlow<Vertical1UiState> get() = _vertical1UiState

    private val _horizontal1UiState = MutableStateFlow(Horizontal1UiState())
    val horizontal1UiState: StateFlow<Horizontal1UiState> get() = _horizontal1UiState

    private val _horizontal1UiStates = mutableStateOf(Horizontal1UiState())
    val horizontal1UiStates: State<Horizontal1UiState> get() = _horizontal1UiStates

    init {
        getHomeBanner()
    }

    private fun getHomeBanner() {
        viewModelScope.launch {
            try {
                homeBannerRepository.getHomeBanner().collect { resources ->
                    when (resources) {
                        is NetworkResult.Success -> {
                            val listBanner = resources.data.data?.layout?.filter { data ->
                                data.type == "banner"
                            }
                            val listVertical1 = resources.data.data?.layout?.filter { data ->
                                data.type == "vertical1"
                            }
                            val listHorizontal1 = resources.data.data?.layout?.filter { data ->
                                data.type == "horizontal1"
                            }
                            _loadingUiState.update {
                                false
                            }
                            _homeBannerUiState.update {
                                HomeBannerUiState(resources.data)
                            }
                            _bannerUiState.update {
                                BannerUiState(listBanner?.get(0)?.banner)
                            }
                            _vertical1UiState.update {
                                Vertical1UiState(listVertical1?.get(0))
                            }
                            _horizontal1UiState.update {
                                Horizontal1UiState(listHorizontal1?.get(0))
                            }
                            _horizontal1UiStates.value
                            Log.d("API Service", "Network Success")
                        }

                        is NetworkResult.Failure -> {
                            _loadingUiState.update {
                                false
                            }
                            _homeBannerUiState.update {
                                HomeBannerUiState(null)
                            }
                            Log.d("API Service", "Network Failed")
                        }

                        is NetworkResult.Loading -> {
                            _loadingUiState.update {
                                true
                            }
                            Log.d("API Service", "Network Loading")
                            //loading
                        }

                        else -> {}
                    }
                }
            } catch (ex: Exception) {
                Log.d("API Service", "Application Exception", ex)
            }
        }
    }
}