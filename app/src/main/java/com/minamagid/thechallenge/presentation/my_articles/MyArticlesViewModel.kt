package com.minamagid.thechallenge.presentation.my_articles

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minamagid.thechallenge.common.Resource
import com.minamagid.thechallenge.domain.model.homeResponses.Result
import com.minamagid.thechallenge.domain.use_case.delete_article.DeleteArticleUseCase
import com.minamagid.thechallenge.domain.use_case.delete_table.DeleteTableUseCase
import com.minamagid.thechallenge.domain.use_case.get_articles_local.GetLocalArticlesUseCase
import com.minamagid.thechallenge.domain.use_case.get_most_emailed.GetMostEmailedArticlesUseCase
import javax.inject.Inject
import com.minamagid.thechallenge.domain.use_case.get_most_viewed.GetMostViewedArticlesAtLastSevenDaysUseCase
import com.minamagid.thechallenge.domain.use_case.get_most_shared.GetMostSharedArticlesOnFacebookUseCase
import com.minamagid.thechallenge.domain.use_case.insert_article.InsertArticleUseCase
import com.minamagid.thechallenge.utils.NetworkManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class MyArticlesViewModel @Inject constructor(
    val getMostViewedArticlesAtLastSevenDaysUseCase: GetMostViewedArticlesAtLastSevenDaysUseCase,
    val getMostEmailedArticlesUseCase: GetMostEmailedArticlesUseCase,
    val getMostSharedArticlesOnFacebookUseCase: GetMostSharedArticlesOnFacebookUseCase,
    private val networkManager: NetworkManager,
    val insertArticleUseCase: InsertArticleUseCase,
    val getLocalArticlesUseCase: GetLocalArticlesUseCase,
    val deleteTableUseCase: DeleteTableUseCase,
    val deleteOneArticleUseCase: DeleteArticleUseCase,

    ) :
    ViewModel() {

    private val _state = MutableStateFlow(MyArticlesState())
    val listDataRemote: MutableLiveData<List<Result>?> = MutableLiveData()
    val type = MutableLiveData(0)
    val isLoad = MutableLiveData(true)
    val networkObserver = networkManager.observeConnectionStatus


    init {
        getLocalArticlesData()
    }


    fun insertArticleLocal(article: Result) {
        insertArticleUseCase(article).onEach { response ->
            when (response) {
                is Resource.Success -> {
                    _state.value = MyArticlesState(isLoading = false)
                }
                is Resource.Error -> {
                    _state.value =
                        MyArticlesState(error = response.message ?: "unexpected error")
                }
                is Resource.Loading -> {
                    _state.value = MyArticlesState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getLocalArticlesData() {
        getLocalArticlesUseCase().onEach { response ->
            when (response) {
                is Resource.Loading -> {
                    _state.value = MyArticlesState(isLoading = true)
                }
                is Resource.Success -> {
                    listDataRemote.value = response.data
//                    _state.value = ArticlesState(
//                        generalResponse = response.data,
//                        isLoading = false
//                    )
                }
                is Resource.Error -> {
                    _state.value =
                        MyArticlesState(error = response.message ?: "unexpected error")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun clearLocalDB() {
        deleteTableUseCase().onEach { response ->
            when (response) {
                is Resource.Loading -> {
                    _state.value = MyArticlesState(isLoading = true)
                }
                is Resource.Success -> {
//                    _state.value = ArticlesState(
//                        generalResponse = response.data,
//                        isLoading = false
//                    )
                }
                is Resource.Error -> {
                    _state.value =
                        MyArticlesState(error = response.message ?: "unexpected error")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun deleteArticleLocalDB(article: Result) {
        deleteOneArticleUseCase(article.id).onEach { response ->
            when (response) {
                is Resource.Loading -> {
                    _state.value = MyArticlesState(isLoading = true)
                }
                is Resource.Success -> {
//                    _state.value = ArticlesState(
//                        generalResponse = response.data,
//                        isLoading = false
//                    )
                }
                is Resource.Error -> {
                    _state.value =
                        MyArticlesState(error = response.message ?: "unexpected error")
                }
            }
        }.launchIn(viewModelScope)
    }


}