package com.minamagid.thechallenge.presentation.articlesScreen

import com.minamagid.thechallenge.domain.model.homeResponses.GeneralResponse


data class ArticlesState(
    val isLoading: Boolean = false,
    val generalResponse: GeneralResponse? = null,
    val error: String = ""
)