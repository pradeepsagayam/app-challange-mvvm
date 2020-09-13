package com.dp.db.search

import com.dp.db.search.repository.RecentSearchInput
import com.dp.db.search.repository.RecentSearchRepository
import com.dp.db.search.repository.model.RecentSearchDetails
import io.reactivex.Observable
import javax.inject.Inject

class RecentSearchDBProvider @Inject constructor(
    private val recentSearchRepository: RecentSearchRepository
) {

    fun getRecentSearchList(searchKey: String?): Observable<RecentSearchDetails> {
        return if (searchKey.isNullOrEmpty()) {
            recentSearchRepository.getAllRecentSearchDetails()
        } else {
            recentSearchRepository.getRecentSearchDetails(RecentSearchInput(searchKey))
        }
    }

    fun insertRecentSearchDetails(searchKey: String) {
        recentSearchRepository.insertRecentSearchDetails(RecentSearchInput(searchKey))
    }
}
