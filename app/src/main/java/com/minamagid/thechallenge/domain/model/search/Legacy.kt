package com.minamagid.thechallenge.domain.model.search


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Legacy(
    @SerializedName("thumbnail")
    var thumbnail: String?,
    @SerializedName("thumbnailheight")
    var thumbnailheight: Int?,
    @SerializedName("thumbnailwidth")
    var thumbnailwidth: Int?,
    @SerializedName("wide")
    var wide: String?,
    @SerializedName("wideheight")
    var wideheight: Int?,
    @SerializedName("widewidth")
    var widewidth: Int?,
    @SerializedName("xlarge")
    var xlarge: String?,
    @SerializedName("xlargeheight")
    var xlargeheight: Int?,
    @SerializedName("xlargewidth")
    var xlargewidth: Int?
)