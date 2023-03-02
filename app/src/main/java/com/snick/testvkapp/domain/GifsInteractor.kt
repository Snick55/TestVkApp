package com.snick.testvkapp.domain

import com.snick.testvkapp.data.GifsRepository

interface GifsInteractor {

   suspend fun fetchGifs(query: String): GifsDomain

   class Base(private val repository: GifsRepository):GifsInteractor{

      override suspend fun fetchGifs(query: String): GifsDomain {
         return repository.fetchGifs(query)
      }
   }

}