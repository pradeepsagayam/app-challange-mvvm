package com.dp.movies.service.di

import com.dp.network.NetworkConfiguration
import com.dp.movies.service.MoviesProvider
import retrofit2.Retrofit

class MoviesApiLibrary(
    private val retrofit: Retrofit,
    private val networkConfiguration: NetworkConfiguration
) {

    private val component = DaggerMoviesApiComponent.builder()
        .apply {
            retrofit(retrofit)
            networkConfiguration(networkConfiguration)
        }.build()

    fun moviesProvider(): MoviesProvider = component.moviesProvider()
}
