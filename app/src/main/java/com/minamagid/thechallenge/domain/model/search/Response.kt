package com.minamagid.thechallenge.domain.model.search


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Response(
    @SerializedName("docs")
    var docs: List<Doc>?,
    @SerializedName("meta")
    var meta: Meta?
)