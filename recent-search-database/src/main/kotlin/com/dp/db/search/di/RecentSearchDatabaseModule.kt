package com.dp.db.search.di

import android.content.Context
import androidx.room.Room
import com.dp.db.search.RecentSearchDaoService
import com.dp.db.search.RecentSearchDatabase
import com.dp.db.search.repository.RecentSearchRandomIdGenerator
import com.dp.db.search.repository.RecentSearchRepository
import com.dp.db.search.repository.RecentSearchRepositoryHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RecentSearchDatabaseModule(context: Context) {

    private val recentSearchDatabase: RecentSearchDatabase = Room.databaseBuilder(
        context,
        RecentSearchDatabase::class.java,
        RecentSearchDatabase.RECENT_SEARCH_DATABASE
    )
        .allowMainThreadQueries()
        .build()

    @Singleton
    @Provides
    fun providesRecentSearchDatabase(): RecentSearchDatabase {
        return recentSearchDatabase
    }

    @Provides
    fun providesRecentSearchDaoService(recentSearchDatabase: RecentSearchDatabase): RecentSearchDaoService {
        return recentSearchDatabase.recentSearchDaoService()
    }

    @Provides
    fun providesRecentSearchRepository(): RecentSearchRepository {
        val recentSearchRandomIdGenerator = RecentSearchRandomIdGenerator()
        val recentSearchRepositoryHelper = RecentSearchRepositoryHelper(recentSearchRandomIdGenerator)
        return RecentSearchRepository(recentSearchDatabase.recentSearchDaoService(), recentSearchRepositoryHelper)
    }

}
