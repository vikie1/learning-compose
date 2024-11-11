package com.github.vikie1.amphibians.data

import com.github.vikie1.amphibians.network.Amphibian
import com.github.vikie1.amphibians.network.AmphibiansApiService

interface AmphibiansRepository {
    suspend fun loadAmphibiansApi(): List<Amphibian>
}

class NetworkAmphibiansRepository(private val amphibiansApiService: AmphibiansApiService): AmphibiansRepository{
    override suspend fun loadAmphibiansApi(): List<Amphibian> {
        return amphibiansApiService.getAmphibians()
    }
}