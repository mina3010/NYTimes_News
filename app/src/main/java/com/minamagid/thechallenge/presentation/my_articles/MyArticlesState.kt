package com.minamagid.thechallenge.presentation.my_articles

import com.minamagid.thechallenge.domain.model.homeResponses.GeneralResponse


data class MyArticlesState(
    val isLoading: Boolean = false,
    val generalResponse: GeneralResponse? = null,
    val error: String = ""
)