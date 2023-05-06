package com.halim.starwars.data.services

import com.halim.starwars.data.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response

/**
 * Handling Network
 */
abstract class SafeApiCall {
    suspend fun <T> safeApiCall(apiCall: suspend () -> T): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        Resource.Failure(throwable.response()?.errorBody().toString(), null)
                    }
                    else -> {
                        Resource.Failure(throwable.localizedMessage, null)
                    }
                }
            }
        }
    }
//    suspend fun <T> safeApiCallV2(apiCall: suspend () -> T): DataResource<T> {
//        return withContext(Dispatchers.IO) {
//            try {
//                if (apiCall.invoke().toString().contains("code=404")) {
//                    val response = apiCall.invoke() as Response<*>
//                    val failure = DataResource.Failure(
//                        false,
//                        response.code(),
//                        response.errorBody(),
//                        response.errorBody().toString()
//                    )
//                    failure
//                } else if (apiCall.invoke().toString().contains("code=401")) {
//                    val response = apiCall.invoke() as Response<*>
//                    val failure = DataResource.TokenExpired(response.errorBody().toString())
//                    failure
//                } else if (apiCall.invoke().toString().contains("code=500")) {
//                    val response = apiCall.invoke() as Response<*>
//                    val failure = DataResource.Failure(
//                        true,
//                        response.code(),
//                        response.errorBody(),
//                        response.errorBody().toString()
//                    )
//                    failure
//                } else {
//                    DataResource.Success(apiCall.invoke())
//                }
//            } catch (throwable: Throwable) {
//                when (throwable) {
//                    is HttpException -> {
//                        DataResource.Failure(
//                            true,
//                            throwable.code(),
//                            throwable.response()?.errorBody(),
//                            throwable.message
//                        )
//                    }
//                    else -> {
//                        if (throwable.message == Constants.NO_INTERNET) {
//                            DataResource.Failure(true, null, null, throwable.message)
//                        } else DataResource.Failure(
//                            true,
//                            Constants.OTHER_ERROR,
//                            null,
//                            throwable.message
//                        ) // CHANGE THIS TO FALSE
//                    }
//                }
//            }
//        }
//    }
}