package com.dp.challenge.movielist

import androidx.paging.PagingData
import io.reactivex.Flowable

interface GetMovieListRxRepository {

    fun getMovies(query: String): Flowable<PagingData<MovieListItemDataModel>>
}
