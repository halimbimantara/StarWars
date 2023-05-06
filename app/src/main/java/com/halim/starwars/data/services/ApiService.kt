package com.halim.starwars.data.services

import com.halim.starwars.data.models.ApiResponseGeneric
import com.halim.starwars.data.models.FilmResponse
import com.halim.starwars.data.models.HomeWorldResponse
import com.halim.starwars.data.models.PeopleCharResponse
import com.halim.starwars.data.models.SpeciesResponse
import com.halim.starwars.data.models.StarShipResponse
import com.halim.starwars.data.models.VehicleResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {
    @GET("people/?page/")
    suspend fun getCharactersList(@Query("page") page: Int): ApiResponseGeneric<PeopleCharResponse>

    @GET
    suspend fun getFilm(@Url url: String): FilmResponse

    @GET
    suspend fun getHomeWorld(@Url url: String): HomeWorldResponse
    @GET
    suspend fun getStarShip(@Url url: String): StarShipResponse
    @GET
    suspend fun getVehicle(@Url url: String): VehicleResponse

    @GET
    suspend fun getSpecies(@Url url: String): SpeciesResponse

}
