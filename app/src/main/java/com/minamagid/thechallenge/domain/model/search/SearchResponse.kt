package com.minamagid.thechallenge.domain.model.search


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class SearchResponse(
    @SerializedName("copyright")
    var copyright: String?,
    @SerializedName("response")
    var response: Response?,
    @SerializedName("status")
    var status: String?
)