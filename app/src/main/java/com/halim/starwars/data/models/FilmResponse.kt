package com.halim.starwars.data.models


import com.google.gson.annotations.SerializedName

data class FilmResponse(
    @SerializedName("characters")
    var characters: List<String?>?,
    @SerializedName("created")
    var created: String?,
    @SerializedName("director")
    var director: String?,
    @SerializedName("edited")
    var edited: String?,
    @SerializedName("episode_id")
    var episodeId: Int?,
    @SerializedName("opening_crawl")
    var openingCrawl: String?,
    @SerializedName("planets")
    var planets: List<String?>?,
    @SerializedName("producer")
    var producer: String?,
    @SerializedName("release_date")
    var releaseDate: String?,
    @SerializedName("species")
    var species: List<String?>?,
    @SerializedName("starships")
    var starships: List<String?>?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("url")
    var url: String?,
    @SerializedName("vehicles")
    var vehicles: List<String?>?
)