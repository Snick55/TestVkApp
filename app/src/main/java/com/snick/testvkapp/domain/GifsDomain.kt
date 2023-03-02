package com.snick.testvkapp.domain

import com.snick.testvkapp.data.AppExceptions
import com.snick.testvkapp.data.GifsDataToDomainMapper
import com.snick.testvkapp.presentation.GifUi
import com.snick.testvkapp.presentation.GifsUi
import java.lang.Exception

interface GifsDomain {

    fun map(mapper: GifsDomainToUiMapper): GifsUi

    data class Success(private val gifs: List<GifDomain>) : GifsDomain {
        override fun map(mapper: GifsDomainToUiMapper): GifsUi {
            return mapper.map(gifs)
        }
    }

    data class Fail(private val e: AppExceptions) : GifsDomain {
        override fun map(mapper: GifsDomainToUiMapper): GifsUi {
            return mapper.map(e)
        }
    }

}


data class GifDomain(
   private val url: String,
   private val id: String,
   private val title: String,
   private val type: String
){

    fun toUi() = GifUi(url, id, title, type)
}