package com.minamagid.thechallenge.domain.model.search


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Keyword(
    @SerializedName("major")
    var major: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("rank")
    var rank: Int?,
    @SerializedName("value")
    var value: String?
)