package com.dp.movies.service

import com.dp.network.NetworkConfiguration
import io.reactivex.Observable
import javax.inject.Inject

class MoviesProvider @Inject constructor(
    private val networkConfiguration: NetworkConfiguration,
    private val moviesService: MoviesService,
    private val moviesResultMapper: MoviesResultMapper
) {

    fun getMovies(query: String, page: Int): Observable<MoviesResult> {
        return moviesService.getMovies(networkConfiguration.getApiKey(), query, page)
            .map { response ->
                moviesResultMapper.mapFrom(response) as MoviesResult
            }
            .onErrorReturn { MoviesResult.Failure }
    }
}
