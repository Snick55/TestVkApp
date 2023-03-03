package com.snick.testvkapp.domain

import com.snick.testvkapp.R
import com.snick.testvkapp.core.ResourceProvider
import com.snick.testvkapp.data.AppExceptions
import com.snick.testvkapp.data.NoInternetException
import com.snick.testvkapp.data.NoSuchGifsExceptions
import com.snick.testvkapp.presentation.GifsUi
import javax.inject.Inject

interface GifsDomainToUiMapper {

    fun map(gifs: List<GifDomain>): GifsUi
    fun map(e: AppExceptions): GifsUi


    class Base @Inject constructor (private val resourceProvider: ResourceProvider): GifsDomainToUiMapper{


        override fun map(gifs: List<GifDomain>): GifsUi {
            return GifsUi.Gifs(gifs.map { it.toUi() })
        }

        override fun map(e: AppExceptions): GifsUi {
            val errorMessage = when(e){
                is NoInternetException -> resourceProvider.getString(R.string.no_internet_connection)
                is NoSuchGifsExceptions -> resourceProvider.getString(R.string.no_such_gifs)
                else -> resourceProvider.getString(R.string.something_went_wrong)
            }
            return GifsUi.Fail(errorMessage)
        }
    }

}