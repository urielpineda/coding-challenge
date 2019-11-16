package com.upineda.codingchallenge

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class TracksRepository {
    val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl("https://itunes.apple.com/")
        .build()

    val tracksApi = retrofit.create(TracksApi::class.java)

    suspend fun getListOfTracks(): Results {
        return tracksApi.getListOfTracks()
    }
}