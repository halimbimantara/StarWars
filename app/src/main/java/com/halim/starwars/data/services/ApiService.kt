package com.halim.starwars.data.services

import com.halim.starwars.data.models.ApiResponseGeneric
import com.halim.starwars.data.models.PeopleCharResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("people/?page/")
    suspend fun getCharactersList(@Query("page") page: Int): ApiResponseGeneric<PeopleCharResponse>
}
