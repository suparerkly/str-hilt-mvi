package com.example.data.detail.model

data class DetailResult(
    var status: String? = "",
    var message: String? = "",
    var data: DetailDataModel? = null
)

data class DetailDataModel(
    var id: Int? = 0,
//    var layout: LayoutItem? = null
)

