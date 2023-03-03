package com.snick.testvkapp.di

import com.snick.testvkapp.data.GifsDataToDomainMapper
import com.snick.testvkapp.data.GifsRepository
import com.snick.testvkapp.data.cloud.CloudDataSource
import dagger.Module
import dagger.Provides


@Module(includes = [NetworkModule::class])
class DataModule {

    @Provides
    fun provideGifsRepository(cloudDataSource: CloudDataSource,mapper: GifsDataToDomainMapper): GifsRepository{
        return GifsRepository.Base(cloudDataSource,mapper)
    }


    @Provides
    fun provideGifsDataToDomainMapper(): GifsDataToDomainMapper{
        return GifsDataToDomainMapper.Base()
    }

}