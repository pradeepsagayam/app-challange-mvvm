package com.dp.db.search.repository

import javax.inject.Inject

class RecentSearchRandomIdGenerator @Inject constructor() {

    fun get(): Long {
        return Math.random().toLong()
    }
}
