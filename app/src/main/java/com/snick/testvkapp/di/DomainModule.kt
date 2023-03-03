package com.snick.testvkapp.di

import com.snick.testvkapp.core.ResourceProvider
import com.snick.testvkapp.data.GifsRepository
import com.snick.testvkapp.domain.GifsDomainToUiMapper
import com.snick.testvkapp.domain.GifsInteractor
import dagger.Module
import dagger.Provides

@Module(includes = [DataModule::class])
class DomainModule {

    @Provides
    fun provideInteractor(repository: GifsRepository): GifsInteractor{
        return GifsInteractor.Base(repository)
    }

    @Provides
    fun provideGifsDomainToUiMapper(resourceProvider: ResourceProvider): GifsDomainToUiMapper{
        return GifsDomainToUiMapper.Base(resourceProvider)
    }


}