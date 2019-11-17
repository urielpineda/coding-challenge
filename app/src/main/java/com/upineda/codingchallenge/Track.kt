package com.upineda.codingchallenge

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
data class Results(
    @Json(name = "resultCount")
    val resultCount: String,
    @Json(name = "results")
    val results: List<Track>
)

@Parcelize
@JsonClass(generateAdapter = true)
data class Track(
    @Json(name = "artistName")
    val artistName: String = "No artist",
    @Json(name = "trackId")
    val trackId: String = "",
    @Json(name = "trackPrice")
    val price: String = "No price",
    @Json(name = "currency")
    val currency: String = "",
    @Json(name = "primaryGenreName")
    val genre: String = "No genre",
    @Json(name = "longDescription")
    val longDescription: String = "",
    @Json(name = "artworkUrl100")
    val artworkUrl100: String = "",
    @Json(name = "trackName")
    val trackName: String = ""
) : Parcelable
