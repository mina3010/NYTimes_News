package com.minamagid.thechallenge.domain.model.homeResponses


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import androidx.room.*
import java.io.Serializable
import kotlin.Result


@Entity(tableName = "result", indices = [Index(value = ["id"], unique = true)])

data class Result(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "id")
    @SerializedName("id")
    var id: Long=0,
    @SerializedName("abstract")
    var `abstract`: String,
    @SerializedName("adx_keywords")
    var adxKeywords: String,
    @SerializedName("asset_id")
    var assetId: Long,
    @SerializedName("byline")
    var byline: String?,
    @ColumnInfo(name = "column")
    @SerializedName("column")
    var column: String="",
    @SerializedName("des_facet")
    var desFacets: List<String> = emptyList(),
    @SerializedName("eta_id")
    var etaId: Int=0,
    @SerializedName("geo_facet")
    var geoFacet: List<String> = emptyList(),
    @SerializedName("media")
    var media: List<Media>,
    @SerializedName("nytdsection")
    var nytdsection: String,
    @SerializedName("org_facet")
    var orgFacet: List<String> = emptyList(),
    @SerializedName("per_facet")
    var perFacet: List<String> = emptyList(),
    @SerializedName("published_date")
    var publishedDate: String,
    @SerializedName("section")
    var section: String,
    @SerializedName("source")
    var source: String,
    @SerializedName("subsection")
    var subsection: String,
    @SerializedName("title")
    var title: String?=null,
    @SerializedName("type")
    var type: String,
    @SerializedName("updated")
    var updated: String,
    @SerializedName("uri")
    var uri: String,
    @SerializedName("url")
    var url: String
) : Serializable{
    constructor() : this(
        0, "", "", 0, null, "", emptyList(), 0, emptyList(), emptyList(), "", emptyList(),
        emptyList(), "", "", "", "", "", "", "", "",
        ""
    )
}