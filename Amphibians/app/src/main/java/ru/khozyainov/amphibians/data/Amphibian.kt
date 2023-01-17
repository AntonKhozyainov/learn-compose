package ru.khozyainov.amphibians.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Amphibian(
    val name: String,
    val type: String,
    val description: String,
    @Json(name = "img_src") val imgSrc: String,
)
