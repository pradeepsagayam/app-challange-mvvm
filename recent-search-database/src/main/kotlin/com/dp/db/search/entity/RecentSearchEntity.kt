package com.dp.db.search.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "recent_search",
    indices = [Index(value = ["searchKey"], unique = true)]
)
class RecentSearchEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "searchKey")
    val searchKey: String
)
