package com.varol.movier.di

import com.varol.movier.viewmodel.MainVM
import com.varol.movier.viewmodel.MoviesVM
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val viewModelModule: Module = module {
    viewModel { MainVM() }
    viewModel { MoviesVM() }
}