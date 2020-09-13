package com.dp.db.search.repository

import com.dp.db.search.entity.RecentSearchEntity
import javax.inject.Inject

class RecentSearchRepositoryHelper @Inject constructor(
    private val randomIdGenerator: RecentSearchRandomIdGenerator
) {

    fun mapRecentSearchInput(recentSearchInput: RecentSearchInput): RecentSearchEntity {
        return RecentSearchEntity(randomIdGenerator.get(), recentSearchInput.searchQuery)
    }
}
