package com.minamagid.thechallenge.domain.model.homeResponses


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class GeneralResponse(
    @SerializedName("copyright")
    var copyright: String?,
    @SerializedName("num_results")
    var numResults: Int?,
    @SerializedName("results")
    var results: List<Result>?,
    @SerializedName("status")
    var status: String?
)