package com.varol.movier

import android.app.Application
import com.varol.movier.di.appModule
import com.varol.movier.di.networkModule
import com.varol.movier.di.useCaseModule
import com.varol.movier.di.viewModelModule
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
                viewModelModule
            )
        )
    }
}