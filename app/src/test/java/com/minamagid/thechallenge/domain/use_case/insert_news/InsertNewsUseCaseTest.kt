package com.minamagid.thechallenge.domain.use_case.insert_news

import com.minamagid.thechallenge.common.Resource
import com.minamagid.thechallenge.data.repository.FakeRepository
import com.minamagid.thechallenge.domain.model.homeResponses.Result
import com.minamagid.thechallenge.domain.repository.Repository
import com.minamagid.thechallenge.domain.use_case.insert_article.InsertArticleUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.lang.Exception

@ExperimentalCoroutinesApi
class InsertArticleUseCaseTest {

    @Mock
    private lateinit var repository: FakeRepository

    private lateinit var insertArticleUseCase: InsertArticleUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        insertArticleUseCase = InsertArticleUseCase(repository)
    }

    @Test
    fun `invoke should emit Success when repository successfully inserts the article`() = runBlockingTest {
        // Given
        val article = Result(id = 1, "Abstract", "Keywords", 123, null, "", emptyList(), 0, emptyList(), emptyList(), "Section", emptyList(), emptyList(), "2023-06-09", "Section", "Source", "Subsection", "Title", "Type", "Updated", "URI", "URL")
        `when`(repository.insertArticle(article)).thenReturn(Unit) // Mock the insertArticle function

        // When
        val flow: Flow<Resource<*>> = insertArticleUseCase(article)
        val result = mutableListOf<Resource<*>>()
        flow.collect { result.add(it) }

        // Then
        assertEquals(1, result.size)
        assertEquals(Resource.Success<Any>("Success"), result[0])
    }

    @Test
    fun `invoke should emit Error with localized message when an exception occurs during article insertion`() = runBlockingTest {
        // Given
        val errorMessage = "An error occurred"
        val article = Result(
            11, "minaaaa", "", 0, null, "", emptyList(), 0, emptyList(), emptyList(), "", emptyList(),
            emptyList(), "", "", "", "", "", "", "", "","")

        val exception = Exception(errorMessage)
        `when`(repository.insertArticle(article)).thenThrow(exception) // Mock the insertArticle function

        // When
        val flow: Flow<Resource<*>> = insertArticleUseCase(article)
        val result = mutableListOf<Resource<*>>()
        flow.collect { result.add(it) }

        // Then
        assertEquals(1, result.size)
        assertEquals(Resource.Error<Any>(errorMessage), result[0])
    }
}
