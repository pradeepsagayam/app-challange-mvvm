package com.dp.db.search.di

import android.content.Context
import com.dp.db.search.repository.RecentSearchRepository

class RecentSearchDatabaseLibrary(context: Context) {

    private val recentSearchComponent: RecentSearchDatabaseComponent = DaggerRecentSearchDatabaseComponent.builder()
        .recentSearchDatabaseModule(RecentSearchDatabaseModule(context))
        .build()

    fun recentSearchRepository(): RecentSearchRepository {
        return recentSearchComponent.recentSearchRepository()
    }

}
