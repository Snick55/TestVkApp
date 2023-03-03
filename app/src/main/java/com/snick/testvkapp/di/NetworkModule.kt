package com.snick.testvkapp.di

import com.snick.testvkapp.data.cloud.CloudDataSource
import com.snick.testvkapp.data.cloud.GifsApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
class NetworkModule {

    @Provides
    fun provideCloudWeatherDataSource(apiService: GifsApiService): CloudDataSource{
        return CloudDataSource.Base(apiService)
    }

    @Provides
    fun provideCurrentWeatherApiService(
        retrofit: Retrofit
    ): GifsApiService {
        return retrofit.create(GifsApiService::class.java)
    }

    @Provides
    fun provideInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return  interceptor
    }

    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor, ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    fun provideRetrofit(
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.giphy.com/v1/gifs/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}