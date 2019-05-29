package com.varol.movier.remote.repositories

import com.varol.movier.base.BaseRepository
import com.varol.movier.base.service
import com.varol.movier.entity.MoviesModel
import com.varol.movier.remote.Api
import com.varol.movier.remote.BaseMoviesResponse
import com.varol.movier.remote.DataHolder
import io.reactivex.Single

class MovieRepository(
    private val api: Api,
    private val langCode: String
) : BaseRepository() {

    fun getMovies(
        movieType: String,
        apiKey: String,
        page: Int = 1,
        language: String = langCode,
        region: String = ""
    ): Single<DataHolder<BaseMoviesResponse<MutableList<MoviesModel>>>> {
        return service.sendRequest(
            api.getMovies(
                movieType,
                apiKey,
                page,
                language,
                region
            )
        )
    }

}