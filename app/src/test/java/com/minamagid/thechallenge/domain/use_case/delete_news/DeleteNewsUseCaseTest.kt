package com.minamagid.thechallenge.domain.use_case.delete_news

import com.minamagid.thechallenge.common.Resource
import com.minamagid.thechallenge.domain.repository.Repository
import com.minamagid.thechallenge.domain.use_case.delete_article.DeleteArticleUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class DeleteArticleUseCaseTest {

    @Mock
    private lateinit var repository: Repository

    private lateinit var deleteArticleUseCase: DeleteArticleUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        deleteArticleUseCase = DeleteArticleUseCase(repository)
    }

    @Test
    fun `invoke should emit Success when repository successfully deletes the article`() = runBlockingTest {
        // Given
        val imdbID = 123L
        `when`(repository.deleteArticle(imdbID)).thenReturn(Unit) // Mock the deleteArticle function

        // When
        val flow: Flow<Resource<*>> = deleteArticleUseCase(imdbID)
        val result = mutableListOf<Resource<*>>()
        flow.collect { result.add(it) }

        // Then
        assertEquals(1, result.size)
        assertEquals(Resource.Success<Any>("Success"), result[0])
    }

    @Test
    fun `invoke should emit Error when an exception occurs during article deletion`() = runBlockingTest {
        // Given
        val imdbID = 123L
        val errorMessage = "An error occurred"
        val exception = Exception(errorMessage)
        `when`(repository.deleteArticle(imdbID)).thenThrow(exception) // Mock the deleteArticle function

        // When
        val flow: Flow<Resource<*>> = deleteArticleUseCase(imdbID)
        val result = mutableListOf<Resource<*>>()
        flow.collect { result.add(it) }

        // Then
        assertEquals(1, result.size)
        assertEquals(Resource.Error<Any>(errorMessage), result[0])
    }
}