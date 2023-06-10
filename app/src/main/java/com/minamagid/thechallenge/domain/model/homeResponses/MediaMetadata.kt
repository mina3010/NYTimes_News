package com.minamagid.thechallenge.domain.model.homeResponses


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class MediaMetadata(
    @SerializedName("format")
    var format: String?,
    @SerializedName("height")
    var height: Int?,
    @SerializedName("url")
    var url: String?,
    @SerializedName("width")
    var width: Int?
)