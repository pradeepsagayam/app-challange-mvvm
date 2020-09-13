package com.dp.db.search

import com.dp.db.search.repository.RecentSearchInput
import com.dp.db.search.repository.RecentSearchRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class recentSearchDBProviderTest {

    @InjectMocks
    private lateinit var subject: RecentSearchDBProvider

    @Mock
    private lateinit var recentSearchRepository: RecentSearchRepository

    @Mock
    private lateinit var recentSearchInput: RecentSearchInput

    @Test
    fun getRecentSearchDetails() {

        subject.getRecentSearchList(recentSearchInput)

        verify(recentSearchRepository).getRecentSearchDetails(recentSearchInput)
    }

    @Test
    fun insertLoginDetails() {
        subject.insertRecentSearchDetails(recentSearchInput)

        verify(recentSearchRepository).insertRecentSearchDetails(recentSearchInput)
    }
}
