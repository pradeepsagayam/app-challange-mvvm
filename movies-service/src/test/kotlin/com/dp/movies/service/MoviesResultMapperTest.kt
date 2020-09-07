package com.dp.movies.service

import com.dp.movies.service.model.MoviesResponse
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.tuple
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesResultMapperTest {

    @InjectMocks
    private lateinit var subject: MoviesResultMapper

    @Test
    fun mapFrom_givenResponse_returnsUserDetailsResultSuccess() {
        val actual = subject.mapFrom(getResponse())

        assertThat(actual.movieData.page).isEqualTo(1)
        assertThat(actual.movieData.totalResults).isEqualTo(100)
        assertThat(actual.movieData.totalPages).isEqualTo(10)
        assertThat(actual.movieData.moviesDetails)
            .extracting(
                "popularity",
                "voteCount",
                "video",
                "posterPath",
                "id",
                "adult",
                "backdropPath",
                "originalLanguage",
                "originalTitle",
                "title",
                "voteAverage",
                "overview",
                "releaseDate"
            ).containsExactly(
                tuple(
                    2.1,
                    101,
                    false,
                    "posterPath1",
                    1,
                    false,
                    "backdropPath1",
                    "originalLanguage1",
                    "originalTitle1",
                    "title1",
                    6.1,
                    "overview1",
                    "releaseDate1"
                ),
                tuple(
                    3.1,
                    102,
                    true,
                    "posterPath2",
                    2,
                    true,
                    "backdropPath2",
                    "originalLanguage2",
                    "originalTitle2",
                    "title2",
                    7.1,
                    "overview2",
                    "releaseDate2"
                )
            )
    }

    private fun getResponse(): MoviesResponse {
        return MoviesResponse(
            1,
            100,
            10,
            listOf(getMovieDetails(1), getMovieDetails(2))
        )
    }

    private fun getMovieDetails(index: Int): MoviesResponse.MovieDetails {
        val popularity = 1.1.plus(index)
        val voteCount = 100 + index
        val voteAverage = 5.1.plus(index)
        return MoviesResponse.MovieDetails(
            popularity,
            voteCount,
            index % 2 == 0,
            "posterPath${index}",
            index,
            index % 2 == 0,
            "backdropPath${index}",
            "originalLanguage${index}",
            "originalTitle${index}",
            listOf(index),
            "title${index}",
            voteAverage,
            "overview${index}",
            "releaseDate${index}"
        )
    }
}
