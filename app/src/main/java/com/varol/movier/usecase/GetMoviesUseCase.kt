package com.varol.movier.usecase

import com.varol.movier.base.BaseUseCase
import com.varol.movier.model.MoviesModel
import com.varol.movier.remote.BaseMoviesResponse
import com.varol.movier.remote.DataHolder
import com.varol.movier.remote.MovieTypes
import com.varol.movier.remote.repositories.MovieRepository
import io.reactivex.Single

class GetMoviesUseCase(
    private val movieRepository: MovieRepository,
    private val apiKey: String,
    private val lang: String
) : BaseUseCase() {

    fun getMoviesByType(
        movieType: MovieTypes,
        page: Int = 1,
        language: String = lang,
        region: String = ""
    ): Single<DataHolder<BaseMoviesResponse<MutableList<MoviesModel>>>> {
        return movieRepository.getMovies(
            movieType.name,
            apiKey,
            page,
            language,
            region
        )

    }
}