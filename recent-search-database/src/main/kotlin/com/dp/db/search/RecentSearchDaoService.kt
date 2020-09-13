package com.dp.db.search

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dp.db.search.entity.RecentSearchEntity

@Dao
interface RecentSearchDaoService {

    @Query("SELECT * FROM recent_search")
    fun getAllRecentSearchDetails(): List<RecentSearchEntity>

    @Query("SELECT * FROM recent_search where searchKey LIKE :queryString")
    fun getRecentSearchDetails(queryString: String): List<RecentSearchEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecentSearchDetails(recentSearchEntity: RecentSearchEntity)
}
