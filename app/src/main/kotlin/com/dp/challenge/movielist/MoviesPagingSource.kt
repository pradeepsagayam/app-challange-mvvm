package com.dp.challenge.movielist

import androidx.paging.rxjava2.RxPagingSource
import com.dp.baseui.SharedPreferencesProvider
import com.dp.challenge.movielist.item.MovieListItemDataModel
import com.dp.db.search.RecentSearchDBProvider
import com.dp.movies.service.MoviesProvider
import com.dp.movies.service.MoviesResult.Success
import io.reactivex.Single
import javax.inject.Inject

class MoviesPagingSource @Inject constructor(
    private val moviesProvider: MoviesProvider,
    private val recentSearchDBProvider: RecentSearchDBProvider,
    private val moviesDataModelFactory: MoviesDataModelFactory,
    private val sharedPreferencesProvider: SharedPreferencesProvider
) : RxPagingSource<MovieDetailsRequest, MovieListItemDataModel>() {

    companion object {
        const val DEFAULT_INDEX = 1
        private const val LAST_SAVED_MOVIE_NAME = "last_saved_movie_name"
        private const val DEFAULT_MOVIE_NAME = "Batman"
    }

    override fun loadSingle(params: LoadParams<MovieDetailsRequest>)
            : Single<LoadResult<MovieDetailsRequest, MovieListItemDataModel>> {

        val position = params.key!!.page ?: DEFAULT_INDEX
        val query = getQuery(params);

        return moviesProvider.getMovies(query, position)
            .map {
                return@map if (it is Success) {
                    sharedPreferencesProvider.save(LAST_SAVED_MOVIE_NAME, query)
                    recentSearchDBProvider.insertRecentSearchDetails(query)
                    toLoadResult(it, query, position)
                } else {
                    loadError(Throwable())
                }
            }.onErrorReturn { throwable -> loadError(throwable) }
    }

    private fun getQuery(params: LoadParams<MovieDetailsRequest>): String {
        return if (null != params.key && params.key!!.query.isNotEmpty()) {
            params.key!!.query
        } else {
            sharedPreferencesProvider.get(LAST_SAVED_MOVIE_NAME, DEFAULT_MOVIE_NAME)
        }
    }

    private fun loadError(throwable: Throwable)
            : LoadResult.Error<MovieDetailsRequest, MovieListItemDataModel> {
        return LoadResult.Error(throwable)
    }

    private fun toLoadResult(success: Success, query: String, position: Int)
            : LoadResult.Page<MovieDetailsRequest, MovieListItemDataModel> {

        val data = moviesDataModelFactory.generateDataModels(success.movieData.moviesDetails)
        val prevPosition = position - 1
        val nextPosition = position + 1

        val prevKey = if (position == DEFAULT_INDEX) {
            null
        } else {
            MovieDetailsRequest(prevPosition, query)
        }

        val nextKey = if (data.isEmpty() || success.movieData.totalPages == position) {
            null
        } else {
            MovieDetailsRequest(nextPosition, query)
        }

        return LoadResult.Page(data = data, prevKey = prevKey, nextKey = nextKey)
    }
}
