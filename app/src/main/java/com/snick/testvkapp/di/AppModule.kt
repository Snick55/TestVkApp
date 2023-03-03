package com.snick.testvkapp.di

import android.content.Context
import com.snick.testvkapp.core.ResourceProvider
import dagger.Module
import dagger.Provides


@Module
class AppModule {

    @Provides
    fun provideResourceProvider(context: Context): ResourceProvider {
        return ResourceProvider.Base(context)
    }
}