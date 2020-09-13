package com.dp.db.search.repository

import com.dp.db.search.RecentSearchDaoService
import com.dp.db.search.entity.RecentSearchEntity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RecentSearchRepositoryTest {

    @InjectMocks
    private lateinit var subject: RecentSearchRepository

    @Mock
    private lateinit var recentSearchDaoService: RecentSearchDaoService

    @Mock
    private lateinit var recentSearchRepositoryHelper: RecentSearchRepositoryHelper

    private lateinit var recentSearchInput: RecentSearchInput
    private lateinit var entity: RecentSearchEntity

    @Before
    fun setUp() {
        recentSearchInput = RecentSearchInput("searchKey")
        entity = RecentSearchEntity(1, "searchKey")
    }

    @Test
    fun getRecentSearchDetails_givenEmptyList_returnsResultWithEmptyList() {
        `when`(recentSearchDaoService.getRecentSearchDetails("%searchKey%"))
            .thenReturn(emptyList())

        val actual = subject.getRecentSearchDetails(recentSearchInput).test()

        actual.assertValue { it.searchQueries.isEmpty() }
    }

    @Test
    fun getRecentSearchDetails_givenEntityList_returnsResultWithList() {
        `when`(recentSearchDaoService.getRecentSearchDetails("%searchKey%"))
            .thenReturn(listOf(entity))

        val actual = subject.getRecentSearchDetails(recentSearchInput).test()

        actual.assertValue { it.searchQueries.size == 1 }
        actual.assertValue { it.searchQueries[0] == "searchKey" }
    }

    @Test
    fun getRecentSearchDetails_givenLoginDaoServiceThrowsException_returnsFailure() {
        `when`(recentSearchDaoService.getRecentSearchDetails("%searchKey%"))
            .thenThrow(RuntimeException(""))

        val actual = subject.getRecentSearchDetails(recentSearchInput).test()

        actual.assertValue { it.searchQueries.isEmpty() }
    }

    @Test
    fun insertRecentSearchDetails() {
        `when`(recentSearchRepositoryHelper.mapRecentSearchInput(recentSearchInput))
            .thenReturn(entity)

        subject.insertRecentSearchDetails(recentSearchInput)

        verify(recentSearchDaoService).insertRecentSearchDetails(entity)
    }

}
