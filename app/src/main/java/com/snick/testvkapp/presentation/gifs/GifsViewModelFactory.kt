package com.snick.testvkapp.presentation.gifs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.snick.testvkapp.domain.GifsDomainToUiMapper
import com.snick.testvkapp.domain.GifsInteractor

class GifsViewModelFactory(
private val interactor: GifsInteractor,
private val mapper: GifsDomainToUiMapper
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == GifsViewModel::class.java)
        return GifsViewModel(interactor, mapper) as T
    }



}