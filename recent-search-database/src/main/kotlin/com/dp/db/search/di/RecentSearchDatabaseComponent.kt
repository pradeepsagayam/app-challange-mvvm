package com.dp.db.search.di

import com.dp.db.search.repository.RecentSearchRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RecentSearchDatabaseModule::class])
interface RecentSearchDatabaseComponent {

    fun recentSearchRepository(): RecentSearchRepository
}
