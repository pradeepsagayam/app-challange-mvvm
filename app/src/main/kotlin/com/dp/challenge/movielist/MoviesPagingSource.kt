package com.dp.challenge.movielist

import androidx.paging.rxjava2.RxPagingSource
import com.dp.movies.service.MoviesProvider
import com.dp.movies.service.MoviesResult.Success
import io.reactivex.Single
import javax.inject.Inject

class MoviesPagingSource @Inject constructor(
    private val moviesProvider: MoviesProvider,
    private val moviesDataModelFactory: MoviesDataModelFactory
) : RxPagingSource<MovieDetailsRequest, MovieListItemDataModel>() {

    companion object {
        const val DEFAULT_INDEX = 1
    }

    override fun loadSingle(params: LoadParams<MovieDetailsRequest>)
            : Single<LoadResult<MovieDetailsRequest, MovieListItemDataModel>> {

        val position = params.key!!.page ?: DEFAULT_INDEX
        val query = params.key!!.query

        return moviesProvider.getMovies(query, position)
            .map {
                return@map if (it is Success) {
                    toLoadResult(it, query, position)
                } else {
                    loadError(Throwable())
                }
            }.onErrorReturn { throwable -> loadError(throwable) }
    }

    private fun loadError(throwable: Throwable)
            : LoadResult.Error<MovieDetailsRequest, MovieListItemDataModel> {
        return LoadResult.Error(throwable)
    }

    private fun toLoadResult(success: Success, query: String, position: Int)
            : LoadResult.Page<MovieDetailsRequest, MovieListItemDataModel> {

        val data = moviesDataModelFactory.generateDataModels(success.movieData.moviesDetails)
        val prevKey = if (position == DEFAULT_INDEX) null
        else MovieDetailsRequest(position - 1, query)
        val nextKey = if (data.isEmpty()) null else MovieDetailsRequest(position + 1, query)

        return LoadResult.Page(data = data, prevKey = prevKey, nextKey = nextKey)
    }
}
