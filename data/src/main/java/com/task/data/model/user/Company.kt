package com.task.data.model.user

import com.google.gson.annotations.SerializedName

data class Company(

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("catchPhrase")
    val catchPhrase: String? = null,

    @SerializedName("bs")
    val bs: String? = null,
)