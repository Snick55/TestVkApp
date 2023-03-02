package com.snick.testvkapp.data

import com.snick.testvkapp.domain.GifsDomain
import java.lang.Exception
import java.net.UnknownHostException

interface GifsDataToDomainMapper {

    fun map(gifs: List<GifData>): GifsDomain
    fun map(e: Exception): GifsDomain

    class Base: GifsDataToDomainMapper{

        override fun map(gifs: List<GifData>): GifsDomain {
            return GifsDomain.Success(gifs.map { it.toDomain() })
        }

        override fun map(e: Exception): GifsDomain {
            val error = when(e){
                is UnknownHostException -> NoInternetException()
                is EmptyResponseException -> NoSuchGifsExceptions()
                else -> SomethingWentWrongException()
            }
            return GifsDomain.Fail(error)
        }
    }
}