package com.snick.testvkapp.domain

import com.snick.testvkapp.data.GifsRepository
import javax.inject.Inject

interface GifsInteractor {

   suspend fun fetchGifs(query: String): GifsDomain

   class Base @Inject constructor (private val repository: GifsRepository):GifsInteractor{

      override suspend fun fetchGifs(query: String): GifsDomain {
         return repository.fetchGifs(query)
      }
   }

}