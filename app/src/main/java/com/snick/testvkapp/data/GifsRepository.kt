package com.snick.testvkapp.data

import com.snick.testvkapp.data.cloud.CloudDataSource
import com.snick.testvkapp.domain.GifsDomain
import javax.inject.Inject

interface GifsRepository {

    suspend fun fetchGifs(query: String): GifsDomain

    fun saveQuery(query: String)
    fun readQuery(): String


    class Base @Inject constructor(
        private val cloudDataSource: CloudDataSource,
        private val mapper: GifsDataToDomainMapper,
        private val preferenceDataSource: PreferenceDataSource
    ) : GifsRepository {


        override suspend fun fetchGifs(query: String): GifsDomain {
            val res = cloudDataSource.fetchGifs(query)
            return res.map(mapper)
        }


        override fun saveQuery(query: String) {
            preferenceDataSource.saveLastQuery(query)
        }

        override fun readQuery(): String {
            return preferenceDataSource.readLastQuery()
        }
    }


}