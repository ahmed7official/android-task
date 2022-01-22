package com.task.data.model.user


import com.google.gson.annotations.SerializedName
import com.task.domain.model.user.Geo

data class Address(
    @SerializedName("street")
    val street: String? = null,
    @SerializedName("suite")
    val suite: String? = null,
    @SerializedName("city")
    val city: String? = null,
    @SerializedName("zipcode")
    val zipcode: String? = null,
    @SerializedName("geo")
    val geo: Geo? = null
)