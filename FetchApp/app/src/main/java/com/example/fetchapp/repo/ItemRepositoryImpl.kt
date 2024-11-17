package com.example.fetchapp.repo

import com.example.fetchapp.data.Item
import com.example.fetchapp.network.ApiResult
import com.example.fetchapp.network.FetchService
import com.example.fetchapp.network.safeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    private val fetchService: FetchService
) : ItemRepository {

    override suspend fun fetchItem(): Flow<ApiResult<Map<Int, List<Item>>>> {
        return safeApiCall {
            fetchService.fetchItemList()
        }.map { result ->
            when (result) {
                is ApiResult.Success -> {
                    val processedItems = processItems(result.data)
                    ApiResult.Success(processedItems)
                }
                is ApiResult.Error -> result
            }
        }
    }

    /**
     * Process the data base on the project requirement
     */
    private fun processItems(items: List<Item>): Map<Int, List<Item>> {
        return items
            .filter { !it.name.isNullOrBlank() }
            .sortedWith(compareBy({ it.listId }, { extractNumPart(it.name) }, { it.name }))
            .groupBy { it.listId }
    }

    /**
     *     Helper function to extract the numeric part of the name
     */
    private fun extractNumPart(name: String?): Int {
        return name?.split(" ")
            ?.lastOrNull()
            ?.toIntOrNull()
            ?: Int.MAX_VALUE
    }
}