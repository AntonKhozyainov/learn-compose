package ru.khozyainov.amphibians.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AmphibianRepositoryImpl @Inject constructor(
    private val amphibiansApi: AmphibiansApi
): AmphibianRepository {

    override suspend fun getAmphibians(): List<Amphibian> =
        amphibiansApi.getAmphibians()
}