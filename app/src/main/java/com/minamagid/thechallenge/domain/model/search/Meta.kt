package com.minamagid.thechallenge.domain.model.search


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Meta(
    @SerializedName("hits")
    var hits: Int?,
    @SerializedName("offset")
    var offset: Int?,
    @SerializedName("time")
    var time: Int?
)