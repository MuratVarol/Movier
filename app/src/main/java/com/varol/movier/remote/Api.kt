package com.varol.movier.remote

import com.varol.movier.model.MoviesModel
import io.reactivex.Single
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Url

interface Api {

    @POST
    fun getMovies(
        @Url url: String,
        @Query(value = "sort_by") sort_by: String,
        @Query(value = "api_key") apiKey: String,
        @Query(value = "page") page: Int,
        @Query(value = "language") language: String,
        @Query(value = "region") region: String
    ): Single<BaseMoviesResponse<MutableList<MoviesModel>>>


}