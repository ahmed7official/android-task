package com.task.data.model.user


import com.google.gson.annotations.SerializedName

data class Geo(
    @SerializedName("lat")
    val lat: String? = null,
    @SerializedName("lng")
    val lng: String? = null
)