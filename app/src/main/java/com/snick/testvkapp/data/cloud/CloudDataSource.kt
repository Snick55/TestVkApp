package com.snick.testvkapp.data.cloud

import android.util.Log
import com.snick.testvkapp.data.EmptyResponseException
import com.snick.testvkapp.data.GifsData
import java.lang.Exception

interface CloudDataSource {

    suspend fun fetchGifs(query: String): GifsData


    class Base(private val service: GifsApiService) : CloudDataSource {
        override suspend fun fetchGifs(query: String): GifsData {
            return try {
                val result = service.fetchGifs(query = query)
                if (result.data.isEmpty()) {
                    GifsData.Fail(EmptyResponseException())
                }else {
                    val gifsData = result.data.map {
                        it.map()
                    }
                    GifsData.Success(gifsData)
                }
            } catch (e: Exception) {
                GifsData.Fail(e)
            }
        }
    }


}