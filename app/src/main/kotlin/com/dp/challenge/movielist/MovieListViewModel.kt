package com.dp.challenge.movielist

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.rxjava2.cachedIn
import com.dp.network.NetworkConfiguration
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MovieListViewModel @Inject constructor(
    private val getMovieListRxRepository: GetMovieListRxRepository,
    private val networkConfiguration: NetworkConfiguration,
    private val compositeDisposable: CompositeDisposable
) : ViewModel(), LifecycleObserver {

    private val movieListViewModels = MutableLiveData<PagingData<MovieListItemDataModel>>()

    val viewModels: LiveData<PagingData<MovieListItemDataModel>>
        get() {
            return movieListViewModels
        }


    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStarted() {
        getMovies("batman")
    }

    private fun getMovies(query: String) {
        val disposable = getMovieListRxRepository
            .getMovies(query)
            .subscribeOn(networkConfiguration.ioScheduler())
            .observeOn(networkConfiguration.mainThreadScheduler())
            .cachedIn(viewModelScope)
            .subscribe {
                movieListViewModels.postValue(it)
            }
        compositeDisposable.add(disposable)
    }
}
