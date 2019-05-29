package com.varol.movier.di

import com.varol.movier.usecase.GetMoviesUseCase
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val useCaseModule: Module = module {
    single { GetMoviesUseCase(get(), get(name = "api_key"), get(name = "lang_code")) }
}