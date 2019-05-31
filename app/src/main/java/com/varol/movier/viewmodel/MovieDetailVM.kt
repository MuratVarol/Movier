package com.varol.movier.viewmodel

import com.varol.movier.base.BaseVM
import com.varol.movier.model.MoviesModel
import com.varol.movier.util.binding_adapters.SingleLiveEvent

class MovieDetailVM : BaseVM() {
    val selectedMovie = SingleLiveEvent<MoviesModel>()


    fun setSelectedMovie(moviesModel: MoviesModel) {
        selectedMovie.postValue(moviesModel)
    }


}