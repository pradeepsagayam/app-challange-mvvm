package com.dp.challenge.movielist

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.dp.challenge.movielist.item.MovieListItemDataModel

class MovieListViewState {
    val searchHistory = MutableLiveData<List<String>>()
    val movieListViewModels = MutableLiveData<PagingData<MovieListItemDataModel>>()
}
