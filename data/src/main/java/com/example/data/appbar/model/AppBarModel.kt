package com.example.data.appbar.model

import com.google.firebase.firestore.PropertyName

data class AppBarModel(
    @get:PropertyName("banner")
    @PropertyName("banner")
    val banner: BannerItem? = null,
)

data class BannerItem(
    @get:PropertyName("img")
    @PropertyName("img")
    val img: List<String>? = null,

    @get:PropertyName("title")
    @PropertyName("title")
    val title: String = "",

    @get:PropertyName("ratio")
    @PropertyName("ratio")
    val ratio: String = "",

    @get:PropertyName("width")
    @PropertyName("width")
    val width: Int = 0,

    @get:PropertyName("scale")
    @PropertyName("scale")
    val scale: String = ""
)
