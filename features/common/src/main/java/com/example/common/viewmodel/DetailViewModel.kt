package com.example.common.viewmodel

import androidx.lifecycle.ViewModel
import com.example.data.appbar.repository.HomeBannerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val homeBannerRepository: HomeBannerRepository
) : ViewModel() {

}