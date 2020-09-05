package com.dp.movies.service


sealed class MoviesResult {

    data class Success(val movieData: MovieData) : MoviesResult()

    object Failure : MoviesResult()

    data class MovieData(
        val page: Int,
        val totalResults: Int,
        val moviesDetails: List<MovieDetails>
    )

    data class MovieDetails(
        val popularity: Double,
        val voteCount: Int,
        val video: Boolean,
        val posterPath: String,
        val id: Int,
        val adult: Boolean,
        val backdropPath: String,
        val originalLanguage: String,
        val originalTitle: String,
        val genreIds: List<Int>,
        val title: String,
        val voteAverage: Double,
        val overview: String,
        val releaseDate: String
    )
}
