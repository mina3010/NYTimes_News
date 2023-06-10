package com.minamagid.thechallenge.presentation.details

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.minamagid.thechallenge.R
import com.minamagid.thechallenge.common.Constants
import javax.inject.Inject
import com.minamagid.thechallenge.domain.use_case.get_most_viewed.GetMostViewedArticlesAtLastSevenDaysUseCase
import com.minamagid.thechallenge.domain.use_case.get_most_shared.GetMostSharedArticlesOnFacebookUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow

@HiltViewModel
class DetailsViewModel @Inject constructor(val getMostViewedArticlesAtLastSevenDaysUseCase: GetMostViewedArticlesAtLastSevenDaysUseCase
                                           , val getMostSharedArticlesOnFacebookUseCase: GetMostSharedArticlesOnFacebookUseCase
) :
    ViewModel() {

}