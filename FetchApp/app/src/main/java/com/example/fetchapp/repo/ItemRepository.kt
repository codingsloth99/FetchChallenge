package com.example.fetchapp.repo

import com.example.fetchapp.data.Item
import com.example.fetchapp.network.ApiResult
import kotlinx.coroutines.flow.Flow

interface ItemRepository {

    /**
     * fetch the list of items and process them accordingly
     */
    suspend fun fetchItem(): Flow<ApiResult<Map<Int, List<Item>>>>
}