package com.snick.testvkapp.di

import android.content.Context
import android.content.SharedPreferences
import com.snick.testvkapp.core.ResourceProvider
import dagger.Module
import dagger.Provides


@Module
class AppModule {

    @Provides
    fun provideResourceProvider(context: Context): ResourceProvider {
        return ResourceProvider.Base(context)
    }

    @Provides
    fun provideSharedPreference(context: Context): SharedPreferences{
        return context.getSharedPreferences(APP_PREF, Context.MODE_PRIVATE)
    }

    private companion object{
        const val APP_PREF = "APP_PREF"
    }
}