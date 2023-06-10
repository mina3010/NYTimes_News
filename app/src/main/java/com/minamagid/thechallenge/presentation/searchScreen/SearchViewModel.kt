package com.minamagid.thechallenge.presentation.searchScreen

import android.view.View
import androidx.lifecycle.*
import androidx.navigation.findNavController
import androidx.paging.*
import com.minamagid.thechallenge.domain.model.search.Doc
import javax.inject.Inject
import com.minamagid.thechallenge.domain.use_case.get_search.GetSearchUseCase
import com.minamagid.thechallenge.presentation.searchScreen.pagedList.SearchPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchUseCase: GetSearchUseCase
) :
    ViewModel() {

    private val pagingConfig = PagingConfig(
        pageSize = 20,
        enablePlaceholders = false
    )

    val queryChannel = MutableStateFlow("")

    val searchResults: LiveData<PagingData<Doc>> = queryChannel
        .asStateFlow()
        .flatMapLatest { query ->
            Pager(pagingConfig) {
                SearchPagingSource(getSearchUseCase, query)
            }.flow
        }
        .asLiveData()

    fun search(query: String) {
        queryChannel.value = query
    }
}