package com.varol.movier.remote.repositories

import com.varol.movier.base.BaseRepository
import com.varol.movier.base.service
import com.varol.movier.model.MoviesModel
import com.varol.movier.remote.Api
import com.varol.movier.remote.BaseMoviesResponse
import com.varol.movier.remote.DataHolder
import io.reactivex.Single

class MovieRepository(
    private val api: Api
) : BaseRepository() {

    fun getMovies(
        movieType: String,
        apiKey: String,
        page: Int = 1,
        language: String,
        url: String = "discover/movie/",
        region: String = ""
    ): Single<DataHolder<BaseMoviesResponse<MutableList<MoviesModel>>>> {
        return service.sendRequest(
            api.getMovies(
                url,
                movieType,
                apiKey,
                page,
                language,
                region
            )
        )
    }

}