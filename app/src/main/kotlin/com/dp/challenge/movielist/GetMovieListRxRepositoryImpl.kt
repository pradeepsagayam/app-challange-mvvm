package com.dp.challenge.movielist

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.RxPagingSource
import androidx.paging.rxjava2.flowable
import com.dp.challenge.movielist.item.MovieListItemDataModel
import io.reactivex.Flowable
import javax.inject.Inject

class GetMovieListRxRepositoryImpl @Inject constructor(
    private val rxPagingSource: RxPagingSource<MovieDetailsRequest, MovieListItemDataModel>
) : GetMovieListRxRepository {

    override fun getMovies(query: String): Flowable<PagingData<MovieListItemDataModel>> {
        val pagingConfig = PagingConfig(pageSize = 7)
        val movieDetailsRequest = MovieDetailsRequest(1, query)

        return Pager(
            config = pagingConfig,
            initialKey = movieDetailsRequest,
            pagingSourceFactory = { rxPagingSource }
        ).flowable
    }
}
