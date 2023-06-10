package com.minamagid.thechallenge.presentation
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.paging.PagingData
import com.minamagid.thechallenge.domain.model.search.Doc
import com.minamagid.thechallenge.domain.use_case.get_search.GetSearchUseCase
import com.minamagid.thechallenge.presentation.searchScreen.SearchViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class SearchViewModelTest {

    @get:Rule
    private val testDispatcher = TestCoroutineDispatcher()

    @Mock
    private lateinit var getSearchUseCase: GetSearchUseCase

    private lateinit var viewModel: SearchViewModel
    private lateinit var searchViewModel: SearchViewModel


    @After
    fun tearDown() {
        Dispatchers.resetMain() // Reset the main dispatcher after the tests
        testDispatcher.cleanupTestCoroutines()
    }

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        viewModel = SearchViewModel(getSearchUseCase)
        Dispatchers.setMain(testDispatcher) // Set the testDispatcher as the main dispatcher for coroutines
        getSearchUseCase = mock()
        searchViewModel = SearchViewModel(getSearchUseCase)
    }

    @Test
    fun `searchResults should emit expected PagingData`() = runBlockingTest {
        val query = "test"
        val pagingData = PagingData.from(listOf(Doc(
            abstract = "Fake Abstract",
            byline = null,
            documentType = "Fake Document Type",
            headline = null,
            id = "Fake ID",
            keywords = null,
            leadParagraph = "Fake Lead Paragraph",
            multimedia = null,
            newsDesk = "Fake News Desk",
            pubDate = "2023-06-10",
            sectionName = "Fake Section Name",
            snippet = "Fake Snippet",
            source = "Fake Source",
            subsectionName = "Fake Subsection Name",
            typeOfMaterial = "Fake Type of Material",
            uri = "Fake URI",
            webUrl = "https://www.example.com",
            wordCount = 1000
        )))
        val observer = mock(Observer::class.java) as Observer<PagingData<Doc>>

        viewModel.searchResults.observeForever(observer)
        viewModel.search(query)

        verify(observer).onChanged(pagingData)
        assertEquals(query, viewModel.queryChannel.value)
    }

    @Test
    fun `onBack should navigate back using NavController`() {
        val navController = mock(NavController::class.java)
        val view = mock(View::class.java)

        verify(view).findNavController()
        verify(navController).popBackStack()
    }
}
