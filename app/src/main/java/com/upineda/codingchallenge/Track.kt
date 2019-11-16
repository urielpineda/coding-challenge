package com.upineda.codingchallenge

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Results(

    @Json(name = "resultCount")
    val resultCount: String,
    @Json(name = "results")
    val results: List<Track>
)

@JsonClass(generateAdapter = true)
data class Track(
    @Json(name = "artistName")
    val artistName: String = "",
    @Json(name = "trackId")
    val trackId: String = "",
    @Json(name = "trackPrice")
    val price: String = "",
    @Json(name = "primaryGenreName")
    val genre: String = "",
    @Json(name = "longDescription")
    val longDescription: String = "",
    @Json(name = "artworkUrl30")
    val artworkUrl30: String = "https://dummyimage.com/30x30/ggg/gggg",
    @Json(name = "artworkUrl60")
    val artworkUrl60: String = "https://dummyimage.com/60x60/ggg/gggg",
    @Json(name = "artworkUrl100")
    val artworkUrl100: String = "https://dummyimage.com/100x100/ggg/gggg",
    @Json(name = "trackName")
    val trackName: String = ""
)
