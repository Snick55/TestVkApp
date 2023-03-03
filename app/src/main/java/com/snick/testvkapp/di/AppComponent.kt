package com.snick.testvkapp.di

import android.content.Context
import com.snick.testvkapp.presentation.gifs.FragmentGifs
import dagger.BindsInstance
import dagger.Component


@Component(modules = [DataModule::class,DomainModule::class,NetworkModule::class,PresentationModule::class,AppModule::class])
interface AppComponent {

    fun inject(fragmentGifs: FragmentGifs)


    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

}