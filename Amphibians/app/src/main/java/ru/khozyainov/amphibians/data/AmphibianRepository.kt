package ru.khozyainov.amphibians.data

interface AmphibianRepository {
    suspend fun getAmphibians(): List<Amphibian>
}