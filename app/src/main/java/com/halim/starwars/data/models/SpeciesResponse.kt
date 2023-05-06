package com.halim.starwars.data.models


import com.google.gson.annotations.SerializedName

data class SpeciesResponse(
    @SerializedName("average_height")
    var averageHeight: String?,
    @SerializedName("average_lifespan")
    var averageLifespan: String?,
    @SerializedName("classification")
    var classification: String?,
    @SerializedName("created")
    var created: String?,
    @SerializedName("designation")
    var designation: String?,
    @SerializedName("edited")
    var edited: String?,
    @SerializedName("eye_colors")
    var eyeColors: String?,
    @SerializedName("films")
    var films: List<String?>?,
    @SerializedName("hair_colors")
    var hairColors: String?,
    @SerializedName("homeworld")
    var homeworld: Any?,
    @SerializedName("language")
    var language: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("people")
    var people: List<String?>?,
    @SerializedName("skin_colors")
    var skinColors: String?,
    @SerializedName("url")
    var url: String?
)