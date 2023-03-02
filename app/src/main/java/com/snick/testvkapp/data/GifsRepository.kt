package com.snick.testvkapp.data

import com.snick.testvkapp.data.cloud.CloudDataSource
import com.snick.testvkapp.domain.GifsDomain

interface GifsRepository {

    suspend fun fetchGifs(query: String): GifsDomain


    class Base(
        private val cloudDataSource: CloudDataSource,
        private val mapper: GifsDataToDomainMapper
    ) : GifsRepository {


        override suspend fun fetchGifs(query: String): GifsDomain {
            val res = cloudDataSource.fetchGifs(query)
            return res.map(mapper)
        }
    }


}