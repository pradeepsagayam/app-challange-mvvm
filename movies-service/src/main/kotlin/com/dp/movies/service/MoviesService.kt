package com.dp.movies.service

import com.dp.movies.service.model.MoviesResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    @GET("movie")
    fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("page") page: Int
    ): Observable<MoviesResponse>
}
