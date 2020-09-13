package com.dp.challenge.movielist

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.rxjava2.cachedIn
import com.dp.baseui.SharedPreferencesProvider
import com.dp.challenge.movielist.item.MovieListItemDataModel
import com.dp.db.search.RecentSearchDBProvider
import com.dp.network.NetworkConfiguration
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MovieListViewModel @Inject constructor(
    private val getMovieListRxRepository: GetMovieListRxRepository,
    private val recentSearchDBProvider: RecentSearchDBProvider,
    private val sharedPreferencesProvider: SharedPreferencesProvider,
    private val networkConfiguration: NetworkConfiguration,
    private val compositeDisposable: CompositeDisposable
) : ViewModel(), LifecycleObserver {

    companion object {

    }

    private val movieListViewState = MovieListViewState()

    val movieViewModels: LiveData<PagingData<MovieListItemDataModel>>
        get() {
            return movieListViewState.movieListViewModels
        }

    val filteredSearchElements: LiveData<List<String>>
        get() {
            return movieListViewState.searchHistory
        }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onViewCreated() {
        getMovies("")
    }

    fun onSearchTextChange(searchValue: String?) {
        val disposable = recentSearchDBProvider.getRecentSearchList(searchValue)
            .subscribeOn(networkConfiguration.ioScheduler())
            .observeOn(networkConfiguration.mainThreadScheduler())
            .subscribe {
                movieListViewState.searchHistory.postValue(it.searchQueries)
            }
        compositeDisposable.add(disposable)
    }

    fun getMovies(query: String?) {
        if (query != null) {
            val disposable = getMovieListRxRepository
                .getMovies(query)
                .subscribeOn(networkConfiguration.ioScheduler())
                .observeOn(networkConfiguration.mainThreadScheduler())
                .cachedIn(viewModelScope)
                .subscribe { page ->
                    movieListViewState.movieListViewModels.postValue(page)
                }
            compositeDisposable.add(disposable)
        }
    }
}
