package ru.khozyainov.amphibians.data

import retrofit2.http.GET

interface AmphibiansApi {

    @GET("amphibians")
    suspend fun getAmphibians(): List<Amphibian>
}