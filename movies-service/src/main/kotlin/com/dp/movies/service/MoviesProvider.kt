package com.dp.movies.service

import com.dp.network.NetworkConfiguration
import io.reactivex.Single
import javax.inject.Inject

class MoviesProvider @Inject constructor(
    private val networkConfiguration: NetworkConfiguration,
    private val moviesService: MoviesService,
    private val moviesResultMapper: MoviesResultMapper
) {

    fun getMovies(query: String, page: Int): Single<MoviesResult> {
        return moviesService.getMovies(networkConfiguration.getApiKey(), query, page)
            .singleOrError()
            .map { response ->
                if (response.results.isNullOrEmpty()) {
                    MoviesResult.Failure(Throwable())
                } else {
                    moviesResultMapper.mapFrom(response) as MoviesResult
                }
            }
            .onErrorReturn { throwable -> MoviesResult.Failure(throwable) }
    }
}
