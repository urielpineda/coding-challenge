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
    val longDescription: String = "No description",
    @Json(name = "artworkUrl30")
    val artworkUrl30: String = "https://dummyimage.com/30x30/aaa/aaa",
    @Json(name = "artworkUrl60")
    val artworkUrl60: String = "https://dummyimage.com/60x60/aaa/aaa",
    @Json(name = "artworkUrl100")
    val artworkUrl100: String = "https://dummyimage.com/100x100/aaa/aaa",
    @Json(name = "trackName")
    val trackName: String = ""
) : Parcelable
