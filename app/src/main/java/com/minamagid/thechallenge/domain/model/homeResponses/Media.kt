package com.minamagid.thechallenge.domain.model.homeResponses


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Media(
    @SerializedName("approved_for_syndication")
    var approvedForSyndication: Int?,
    @SerializedName("caption")
    var caption: String?,
    @SerializedName("copyright")
    var copyright: String?,
    @SerializedName("media-metadata")
    var mediaMetadata: List<MediaMetadata>?,
    @SerializedName("subtype")
    var subtype: String?,
    @SerializedName("type")
    var type: String?
)