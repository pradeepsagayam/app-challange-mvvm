package com.dp.movies.service

import com.dp.movies.service.MoviesResult.*
import com.dp.movies.service.model.MoviesResponse
import javax.inject.Inject

class MoviesResultMapper @Inject constructor() {

    fun mapFrom(response: MoviesResponse): Success {
        val movieData = MovieData(
            response.page,
            response.totalResults,
            response.results.map { movieDetail -> movieDetail.convert() })

        return Success(movieData)
    }

    private fun MoviesResponse.MovieDetails.convert(): MovieDetails {
        return MovieDetails(
            popularity,
            voteCount,
            video,
            posterPath,
            id,
            adult,
            backdropPath,
            originalLanguage,
            originalTitle,
            genreIds,
            title,
            voteAverage,
            overview,
            releaseDate
        )
    }
}
