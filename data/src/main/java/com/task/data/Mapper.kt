package com.task.data

import com.task.data.model.user.Address
import com.task.data.model.user.Company
import com.task.data.model.user.Geo
import com.task.data.model.user.User

fun Address.map() = com.task.domain.model.user.Address(
    street = street,
    suite = suite,
    city = city,
    zipcode = zipcode,
    geo = geo
)

fun Geo.map() = com.task.domain.model.user.Geo(lat = lat, lng = lng)

fun Company.map() = com.task.domain.model.user.Company(
    name = name, catchPhrase = catchPhrase, bs = bs,
)

fun User.map() = com.task.domain.model.user.User(
    id = id,
    name = name,
    username = username,
    email = email,
    address = address?.map(),
    phone = phone,
    website = website,
    company = company?.map(),
)