package com.upineda.codingchallenge

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class TracksRepository {
    val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl("sample.com")
        .build()

    val tracksApi = retrofit.create(TracksApi::class.java)

    suspend fun getListOfTracks(): List<Track> {
        return tracksApi.getListOfTracks()
    }

}