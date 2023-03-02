package com.snick.testvkapp.presentation.gifs


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snick.testvkapp.domain.GifsDomainToUiMapper
import com.snick.testvkapp.domain.GifsInteractor
import com.snick.testvkapp.presentation.GifsUi
import kotlinx.coroutines.launch

class GifsViewModel(
    private val interactor: GifsInteractor,
    private val mapper: GifsDomainToUiMapper
):ViewModel() {

    private val _gifs = MutableLiveData<GifsUi>(GifsUi.Loading())
    val gifs: LiveData<GifsUi> = _gifs

    init {
        fetchGifs()
    }

    fun fetchGifs(query: String ="funny") = viewModelScope.launch{
      val res =  interactor.fetchGifs(query)
        _gifs.value = res.map(mapper)
    }
}