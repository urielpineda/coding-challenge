package com.upineda.codingchallenge

import retrofit2.http.GET

interface TracksApi {
    @GET("search?term=star&amp;country=au&amp;media=movie&amp;all")
    suspend fun getListOfTracks(): List<Track>
}