package com.minamagid.thechallenge.presentation.home

import com.minamagid.thechallenge.domain.model.homeResponses.GeneralResponse


data class MainState(
    val isLoading: Boolean = false,
    val generalResponse: GeneralResponse? = null,
    val error: String = ""
)