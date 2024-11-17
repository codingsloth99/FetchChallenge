package com.example.fetchapp.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

/**
 * Wrapper class handling api result in a structured manner
 */
sealed class ApiResult<out T> {
    data class Success<T>(val data: T): ApiResult<T>()
    data class Error(val e: Throwable): ApiResult<Nothing>()
}

/**
 * Helper method for api call centralizing state handling
 */
suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Flow<ApiResult<T>> = flow {
    val response = apiCall()
    if (response.isSuccessful) {
        response.body()?.let { data ->
            emit(ApiResult.Success(data))
        } ?: run {
            emit(ApiResult.Error(Exception("Response body is null")))
        }
    } else {
        emit(ApiResult.Error(Exception("Error: ${response.code()} - ${response.message()}")))
    }
}.catch { e ->
    emit(ApiResult.Error(e))
}.flowOn(Dispatchers.IO)