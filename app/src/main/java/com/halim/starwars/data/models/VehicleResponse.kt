package com.halim.starwars.data.models


import com.google.gson.annotations.SerializedName

data class VehicleResponse(
    @SerializedName("cargo_capacity")
    var cargoCapacity: String?,
    @SerializedName("consumables")
    var consumables: String?,
    @SerializedName("cost_in_credits")
    var costInCredits: String?,
    @SerializedName("created")
    var created: String?,
    @SerializedName("crew")
    var crew: String?,
    @SerializedName("edited")
    var edited: String?,
    @SerializedName("films")
    var films: List<String?>?,
    @SerializedName("length")
    var length: String?,
    @SerializedName("manufacturer")
    var manufacturer: String?,
    @SerializedName("max_atmosphering_speed")
    var maxAtmospheringSpeed: String?,
    @SerializedName("model")
    var model: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("passengers")
    var passengers: String?,
    @SerializedName("pilots")
    var pilots: List<String?>?,
    @SerializedName("url")
    var url: String?,
    @SerializedName("vehicle_class")
    var vehicleClass: String?
)