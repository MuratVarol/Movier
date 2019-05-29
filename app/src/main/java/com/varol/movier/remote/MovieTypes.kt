package com.varol.movier.remote

sealed class MovieTypes {
    object Popular : MovieTypes()
    object TopRated : MovieTypes()

    val name: String
        get() = when (this) {
            is Popular -> "popular"
            is TopRated -> "top_rated"
        }


}