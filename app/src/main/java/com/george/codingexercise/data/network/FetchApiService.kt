package com.george.codingexercise.data.network

import com.george.codingexercise.data.network.response.ItemsResponse
import retrofit2.http.GET

interface FetchApiService {

    @GET("/hiring.json")
    suspend fun getItems() : List<ItemsResponse>
}