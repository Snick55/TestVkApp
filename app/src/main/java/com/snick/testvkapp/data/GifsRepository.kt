package com.snick.testvkapp.data

import com.snick.testvkapp.data.cloud.CloudDataSource
import com.snick.testvkapp.domain.GifsDomain
import javax.inject.Inject

interface GifsRepository {

    suspend fun fetchGifs(query: String): GifsDomain


    class Base @Inject constructor (
        private val cloudDataSource: CloudDataSource,
        private val mapper: GifsDataToDomainMapper
    ) : GifsRepository {


        override suspend fun fetchGifs(query: String): GifsDomain {
            val res = cloudDataSource.fetchGifs(query)
            return res.map(mapper)
        }
    }


}