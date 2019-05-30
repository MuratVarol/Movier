package com.varol.movier.di

import com.varol.movier.remote.repositories.MovieRepository
import org.koin.dsl.module.Module
import org.koin.dsl.module.module


val repositoryModule: Module = module {
    single { MovieRepository(get()) }
}
