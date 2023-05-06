package com.halim.starwars.data.models

import com.google.gson.annotations.SerializedName

class ApiResponseGeneric<T>(
    @SerializedName("results")
    var results: List<T?>? = listOf(),
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: Any,
)