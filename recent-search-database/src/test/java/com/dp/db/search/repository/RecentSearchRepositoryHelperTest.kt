package com.dp.db.search.repository

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RecentSearchRepositoryHelperTest {

    @InjectMocks
    private lateinit var subject: RecentSearchRepositoryHelper

    @Mock
    private lateinit var randomIdGenerator: RecentSearchRandomIdGenerator

    @Test
    fun mapRecentSearchInput() {
        `when`(randomIdGenerator.get()).thenReturn(10L)

        val actual = subject.mapRecentSearchInput(RecentSearchInput("searchKey"))

        assertThat(actual).extracting("id", "searchKey").containsExactly(10L, "searchKey")
    }
}
