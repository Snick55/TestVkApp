package com.snick.testvkapp.core

import android.app.Application
import com.snick.testvkapp.data.GifsDataToDomainMapper
import com.snick.testvkapp.data.GifsRepository
import com.snick.testvkapp.data.cloud.CloudDataSource
import com.snick.testvkapp.data.cloud.GifsApiService
import com.snick.testvkapp.domain.GifsDomainToUiMapper
import com.snick.testvkapp.domain.GifsInteractor
import com.snick.testvkapp.presentation.gifs.GifsViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App: Application() {

    lateinit var viewModel: GifsViewModel

    override fun onCreate() {
        super.onCreate()
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
        val retrofit =Retrofit.Builder().baseUrl("https://api.giphy.com/v1/gifs/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(GifsApiService::class.java)
        val cloudDataSource = CloudDataSource.Base(service)
        val dataToDomain = GifsDataToDomainMapper.Base()
        val repository = GifsRepository.Base(cloudDataSource, dataToDomain)
        val interactor = GifsInteractor.Base(repository)
        val resourceProvider = ResourceProvider.Base(this)
        val domainToUi = GifsDomainToUiMapper.Base(resourceProvider)
        viewModel = GifsViewModel(interactor,domainToUi)
    }
}