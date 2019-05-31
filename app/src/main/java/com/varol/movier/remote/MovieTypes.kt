package com.varol.movier.remote

sealed class MovieTypes {
    object Popular : MovieTypes()
    object TopRated : MovieTypes()
    object Revenue : MovieTypes()

    val name: String
        get() = when (this) {
            is Popular -> "popularity.desc"
            is TopRated -> "vote_average.desc"
            is Revenue -> "revenue.desc"
        }


}