package com.snick.testvkapp.data.cloud

import retrofit2.http.GET
import retrofit2.http.Query

interface GifsApiService {
    @GET("search?")
    suspend fun fetchGifs(
        @Query("api_key") key: String = "EELP4BDDr2RNlpRHY8zmvq6szZAtLwDi",
        @Query("q") query: String = "funny",
        @Query("limit") limit:Int = 25
    ):Response
}