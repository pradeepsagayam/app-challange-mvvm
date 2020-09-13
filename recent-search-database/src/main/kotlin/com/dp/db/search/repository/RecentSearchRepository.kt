package com.dp.db.search.repository

import com.dp.db.search.RecentSearchDaoService
import com.dp.db.search.repository.model.RecentSearchDetails
import io.reactivex.Observable
import javax.inject.Inject

class RecentSearchRepository @Inject constructor(
    private val recentSearchDaoService: RecentSearchDaoService,
    private val recentSearchRepositoryHelper: RecentSearchRepositoryHelper
) {

    fun getAllRecentSearchDetails(): Observable<RecentSearchDetails> {
        val recentSearchDetails = try {
            recentSearchDaoService.getAllRecentSearchDetails()
                .ifEmpty { emptyList() }
                .map { it.searchKey }
        } catch (ex: Exception) {
            emptyList<String>()
        }
        return Observable.just(RecentSearchDetails(recentSearchDetails))
    }

    fun getRecentSearchDetails(recentSearchInput: RecentSearchInput): Observable<RecentSearchDetails> {

        val recentSearchDetails = try {
            val searchQuery = formatQuery(recentSearchInput.searchQuery)
            recentSearchDaoService.getRecentSearchDetails("%${searchQuery}%")
                .ifEmpty { emptyList() }
                .map { it.searchKey }
        } catch (ex: Exception) {
            emptyList<String>()
        }

        return Observable.just(RecentSearchDetails(recentSearchDetails))
    }

    fun insertRecentSearchDetails(recentSearchInput: RecentSearchInput) {
        val loginDetails = recentSearchRepositoryHelper.mapRecentSearchInput(recentSearchInput)
        recentSearchDaoService.insertRecentSearchDetails(loginDetails)
    }

    private fun formatQuery(searchQuery: String): String {
        return "%${searchQuery}%"
    }
}
