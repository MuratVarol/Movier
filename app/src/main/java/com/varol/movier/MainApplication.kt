package com.varol.movier

import android.app.Application
import com.varol.movier.di.*
import org.koin.android.ext.android.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin(
            this,
            listOf(
                appModule,
                networkModule,
                useCaseModule,
                viewModelModule,
                repositoryModule
            )
        )
    }
}