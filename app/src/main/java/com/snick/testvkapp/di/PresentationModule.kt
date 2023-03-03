package com.snick.testvkapp.di

import com.snick.testvkapp.domain.GifsDomainToUiMapper
import com.snick.testvkapp.domain.GifsInteractor
import com.snick.testvkapp.presentation.gifs.GifsViewModelFactory
import dagger.Module
import dagger.Provides

@Module(includes = [DomainModule::class])
class PresentationModule {

    @Provides
    fun provideGifsViewModelFactory(interactor: GifsInteractor,mapper: GifsDomainToUiMapper):GifsViewModelFactory{
        return GifsViewModelFactory(interactor, mapper)
    }




}