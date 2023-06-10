package com.minamagid.thechallenge.domain.use_case.get_news_local
import com.minamagid.thechallenge.common.Resource
import com.minamagid.thechallenge.domain.model.homeResponses.Result
import com.minamagid.thechallenge.domain.repository.Repository
import com.minamagid.thechallenge.domain.use_case.get_articles_local.GetLocalArticlesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.HttpException
import java.io.IOException

@ExperimentalCoroutinesApi
class GetLocalArticlesUseCaseTest {

    @Mock
    private lateinit var repository: Repository

    private lateinit var getLocalArticlesUseCase: GetLocalArticlesUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        getLocalArticlesUseCase = GetLocalArticlesUseCase(repository)
    }

    @Test
    fun `invoke should emit Success with articles when repository successfully retrieves the articles`() = runBlockingTest {
        // Given
        val articles = listOf(Result(id = 1, "Abstract", "Keywords", 123, null, "", emptyList(), 0, emptyList(), emptyList(), "Section", emptyList(), emptyList(), "2023-06-09", "Section", "Source", "Subsection", "Title", "Type", "Updated", "URI", "URL"))
        `when`(repository.getArticles()).thenReturn(articles) // Mock the getArticles function

        // When
        val flow: Flow<Resource<List<Result>>> = getLocalArticlesUseCase()
        val result = mutableListOf<Resource<List<Result>>>()
        flow.collect { result.add(it) }

        // Then
        assertEquals(1, result.size)
        assertEquals(Resource.Success<List<Result>>(articles), result[0])
    }

    @Test
    fun `invoke should emit Error with localized message when an HttpException occurs during article retrieval`() = runBlockingTest {
        // Given
        val errorMessage = "An error occurred"
        val exception = HttpException(null)
        `when`(repository.getArticles()).thenThrow(exception) // Mock the getArticles function

        // When
        val flow: Flow<Resource<List<Result>>> = getLocalArticlesUseCase()
        val result = mutableListOf<Resource<List<Result>>>()
        flow.collect { result.add(it) }

        // Then
        assertEquals(1, result.size)
        assertEquals(Resource.Error<List<Result>>(errorMessage), result[0])
    }

    @Test
    fun `invoke should emit Error with custom message when an IOException occurs during article retrieval`() = runBlockingTest {
        // Given
        val errorMessage = "No Internet Connection, Check your Internet"
        val exception = IOException()
        `when`(repository.getArticles()).thenThrow(exception) // Mock the getArticles function

        // When
        val flow: Flow<Resource<List<Result>>> = getLocalArticlesUseCase()
        val result = mutableListOf<Resource<List<Result>>>()
        flow.collect { result.add(it) }

        // Then
        assertEquals(1, result.size)
        assertEquals(Resource.Error<List<Result>>(errorMessage), result[0])
    }
}
