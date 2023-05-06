package com.halim.starwars.data.services

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

/**
 * Handling Network
 */
abstract class SafeApiCall {
    suspend fun <T> safeApiCall(apiCall: suspend () -> T): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        Resource.error(throwable.response()?.errorBody().toString(), null)
                    }
                    else -> {
                        Resource.error(throwable.localizedMessage, null)
                    }
                }
            }
        }
    }
}