package com.minamagid.thechallenge.presentation.searchScreen.pagedList

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.minamagid.thechallenge.domain.model.search.Doc
import com.minamagid.thechallenge.domain.use_case.get_search.GetSearchUseCase


class SearchPagingSource(private val searchService: GetSearchUseCase, private val query: String) : PagingSource<Int, Doc>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Doc> {
        val page = params.key ?: 1
        val size = params.loadSize

        return try {
            var data = emptyList<Doc>()
            val searchResults = searchService.invoke(query, page).collect{
                data = it.data?.response?.docs!!
            }
            LoadResult.Page(
                data = data,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (data.isNotEmpty()) page + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, Doc>): Int? {
        return null
    }
}