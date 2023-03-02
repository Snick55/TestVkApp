package com.snick.testvkapp.data

import com.snick.testvkapp.domain.GifDomain
import com.snick.testvkapp.domain.GifsDomain
import java.lang.Exception


interface GifsData {

    fun map(mapper: GifsDataToDomainMapper): GifsDomain

    data class Success(
        private val gifs: List<GifData>
    ) : GifsData {
        override fun map(mapper: GifsDataToDomainMapper): GifsDomain {
            return mapper.map(gifs)
        }
    }

    data class Fail(private val e: Exception) : GifsData {
        override fun map(mapper: GifsDataToDomainMapper): GifsDomain {
            return mapper.map(e)
        }
    }
}


data class GifData(
    private val url: String,
    private val id: String,
    private val title: String,
    private val type: String
) {
    fun toDomain() = GifDomain(url, id, title, type)
}
