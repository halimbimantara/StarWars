package com.halim.starwars.data.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PeopleCharResponse(
    @SerializedName("birth_year")
    var birthYear: String?,
    @SerializedName("created")
    var created: String?,
    @SerializedName("edited")
    var edited: String?,
    @SerializedName("eye_color")
    var eyeColor: String?,
    @SerializedName("films")
    var films: List<String>,
    @SerializedName("gender")
    var gender: String?,
    @SerializedName("hair_color")
    var hairColor: String?,
    @SerializedName("height")
    var height: String?,
    @SerializedName("homeworld")
    var homeworld: String?,
    @SerializedName("mass")
    var mass: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("skin_color")
    var skinColor: String?,
    @SerializedName("species")
    var species: List<String>,
    @SerializedName("starships")
    var starships: List<String>,
    @SerializedName("url")
    var url: String?,
    @SerializedName("vehicles")
    var vehicles: List<String>
): Parcelable