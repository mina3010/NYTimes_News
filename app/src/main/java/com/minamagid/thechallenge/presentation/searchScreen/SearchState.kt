package com.minamagid.thechallenge.presentation.searchScreen

import com.minamagid.thechallenge.domain.model.homeResponses.GeneralResponse


data class SearchState(
    val isLoading: Boolean = false,
    val generalResponse: GeneralResponse? = null,
    val error: String = ""
)