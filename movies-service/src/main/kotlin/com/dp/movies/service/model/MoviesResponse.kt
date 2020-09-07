package com.dp.movies.service.model

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    val page: Int,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    val results: List<MovieDetails>
) {
    data class MovieDetails(
        val popularity: Double,
        @SerializedName("vote_count")
        val voteCount: Int,
        val video: Boolean,
        @SerializedName("poster_path")
        val posterPath: String?,
        val id: Int,
        val adult: Boolean,
        @SerializedName("backdrop_path")
        val backdropPath: String?,
        @SerializedName("original_language")
        val originalLanguage: String,
        @SerializedName("original_title")
        val originalTitle: String,
        @SerializedName("genre_ids")
        val genreIds: List<Int>,
        val title: String,
        @SerializedName("vote_average")
        val voteAverage: Double,
        val overview: String,
        @SerializedName("release_date")
        val releaseDate: String?
    )
}
