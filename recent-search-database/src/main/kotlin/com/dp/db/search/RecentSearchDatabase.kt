package com.dp.db.search

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dp.db.search.entity.RecentSearchEntity

@Database(entities = [RecentSearchEntity::class], version = RecentSearchDatabase.VERSION)
abstract class RecentSearchDatabase : RoomDatabase() {

    abstract fun recentSearchDaoService(): RecentSearchDaoService

    companion object {
        const val RECENT_SEARCH_DATABASE = "recent_search_db"
        const val VERSION = 1
    }
}
