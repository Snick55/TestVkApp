package com.snick.testvkapp.di

import android.content.SharedPreferences
import com.snick.testvkapp.data.GifsDataToDomainMapper
import com.snick.testvkapp.data.GifsRepository
import com.snick.testvkapp.data.PreferenceDataSource
import com.snick.testvkapp.data.cloud.CloudDataSource
import dagger.Module
import dagger.Provides


@Module(includes = [NetworkModule::class,AppModule::class])
class DataModule {

    @Provides
    fun provideGifsRepository(cloudDataSource: CloudDataSource,mapper: GifsDataToDomainMapper,preferenceDataSource: PreferenceDataSource): GifsRepository{
        return GifsRepository.Base(cloudDataSource,mapper,preferenceDataSource)
    }


    @Provides
    fun provideGifsDataToDomainMapper(): GifsDataToDomainMapper{
        return GifsDataToDomainMapper.Base()
    }

    @Provides
    fun providePreferenceDataSource(sharedPreferences: SharedPreferences): PreferenceDataSource{
        return PreferenceDataSource.Base(sharedPreferences)
    }

}