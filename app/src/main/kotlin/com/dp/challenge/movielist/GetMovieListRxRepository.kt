package com.dp.challenge.movielist

import androidx.paging.PagingData
import com.dp.challenge.movielist.item.MovieListItemDataModel
import io.reactivex.Flowable

interface GetMovieListRxRepository {

    fun getMovies(query: String): Flowable<PagingData<MovieListItemDataModel>>
}
