package com.example.fetchapp.network

import com.example.fetchapp.data.Item
import retrofit2.Response
import retrofit2.http.GET

interface FetchService {

    @GET("hiring.json")
    suspend fun fetchItemList(): Response<List<Item>>
}