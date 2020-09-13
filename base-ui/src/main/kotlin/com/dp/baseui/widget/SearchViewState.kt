package com.dp.baseui.widget

import androidx.lifecycle.MutableLiveData

class SearchViewState {
    val searchQuery = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val loadingSpinnerVisibility = MutableLiveData<Boolean>()
    val loginState = MutableLiveData<Boolean>()
    val countryName = MutableLiveData<String>()
}
