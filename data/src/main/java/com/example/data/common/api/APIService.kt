package com.example.data.common.api

import com.example.data.home.model.HomeResultModel
import retrofit2.Response
import retrofit2.http.GET

interface APIService {
    @GET("84c0cdc7-63b9-4232-aa05-0a1399f0f1af")
    suspend fun getHomeResults(
    ) : HomeResultModel

//    @GET("dcd539a1-ee17-4e54-a9df-c3d256f453f9")
//    suspend fun getDetailResults(bannerId: String) :
}