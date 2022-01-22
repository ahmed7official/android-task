package com.task.domain.model.user


data class Address(
    val street: String? = null,
    val suite: String? = null,
    val city: String? = null,
    val zipcode: String? = null,
    val geo: Geo? = null
)