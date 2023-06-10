package com.minamagid.thechallenge.domain.model.search


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Byline(
    @SerializedName("organization")
    var organization: Any?,
    @SerializedName("original")
    var original: Any?,
    @SerializedName("person")
    var person: List<Any>?
)